package com.bestseller.assignment.starbux.controller;

import com.bestseller.assignment.starbux.converter.CartConverter;
import com.bestseller.assignment.starbux.converter.ProductConverter;
import com.bestseller.assignment.starbux.dto.CartDTO;
import com.bestseller.assignment.starbux.dto.ProductDTO;
import com.bestseller.assignment.starbux.service.exception.CartNotFoundException;
import com.bestseller.assignment.starbux.service.exception.ProductNotFoundException;
import com.bestseller.assignment.starbux.service.exception.ToppingNotFoundException;
import com.bestseller.assignment.starbux.service.product.ProductService;
import com.bestseller.assignment.starbux.service.shopping.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This controller is purposely defined for the administrative-related endpoints
 */
@RestController
@RequestMapping(path = "/v1/admin")
public class AdminController {

    private CartService cartService;
    private ProductService productService;
    private ProductConverter productConverter;
    private CartConverter cartConverter;


    public AdminController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
        productConverter = new ProductConverter();
        cartConverter = new CartConverter();
    }

    @ApiOperation(value = "creates a product or topping")
    @PostMapping(path = "product/create", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(
                            productConverter.toResource(productService.save(
                                    productConverter.toModel(productDTO))
                            )
                    );
        } catch (DataIntegrityViolationException dive) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data integrity violation");
        } catch (TransactionSystemException tse) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data constraint violation");
        }

    }

    @ApiOperation(value = "update a product or topping")
    @PutMapping(path = "product/update", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(
                            productConverter.toResource(productService.update(
                                    productConverter.toModel(productDTO))
                            )
                    );
        } catch (DataIntegrityViolationException dive) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data integrity violation");
        } catch (TransactionSystemException tse) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data constraint violation");
        }
    }

    @ApiOperation(value = "get a product or topping")
    @GetMapping(path = "product/get/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getProduct(@PathVariable(value = "name") String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(
                            productConverter.toResource(productService.findByName(name)
                            )
                    );
        } catch (ProductNotFoundException pnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, pnfe.getMessage());
        } catch (DataIntegrityViolationException dive) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data integrity violation");
        } catch (TransactionSystemException tse) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data constraint violation");
        }
    }

    @ApiOperation(value = "delete a product or topping")
    @DeleteMapping(path = "product/delete/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable(value = "name") String name) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(

                            productConverter.toResource(productService.deleteByName(name))

                    );
        } catch (ProductNotFoundException pnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, pnfe.getMessage());
        }
    }

    @ApiOperation(value = "Returns amount for (a) customer(s).")
    @GetMapping(path = "/report/{name}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CartDTO>> getAmountForCustomer(@PathVariable(value = "name") String name) {
        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(
                            cartConverter.toResourceList(cartService.getAmountForCustomer(name))
                    );
        } catch (CartNotFoundException cnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, cnfe.getMessage());
        }
    }

    @ApiOperation(value = "Returns popular topping")
    @GetMapping(path = "/report/topping", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> getPopularTopping() {
        try {
            return ResponseEntity.status(HttpStatus.OK).
                    body(
                            productConverter.toResource(cartService.getPopularTopping())
                    );
        } catch (ToppingNotFoundException tnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, tnfe.getMessage());
        }
    }

}
