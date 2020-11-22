package com.bestseller.assignment.starbux.controller;

import com.bestseller.assignment.starbux.converter.CartConverter;
import com.bestseller.assignment.starbux.dto.CartDTO;
import com.bestseller.assignment.starbux.service.exception.OrderItemNotFoundException;
import com.bestseller.assignment.starbux.service.shopping.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * This controller is defined purposely for make an order
 */
@RestController
@RequestMapping(path = "/v1/cart")
public class CartController {

    private CartService cartService;
    private CartConverter cartConverter;


    public CartController(CartService cartService) {
        this.cartService = cartService;
        cartConverter = new CartConverter();
    }

    @ApiOperation(value = "Makes an order.")
    @PostMapping(path = "/order", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CartDTO> makeOrder(@RequestBody CartDTO cartDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(
                            cartConverter.toResource(cartService.save(
                                    cartConverter.toModel(cartDTO))
                            )
                    );
        } catch (DataIntegrityViolationException dive) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data integrity violation");
        } catch (TransactionSystemException tse) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data constraint violation");
        } catch (OrderItemNotFoundException oinf) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Order item not found");
        } catch (InvalidDataAccessApiUsageException idaaue) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Products should be saved first");
        }
    }

}
