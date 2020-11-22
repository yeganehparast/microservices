package com.bestseller.assignment.starbux.service.product;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.service.exception.ProductNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;

import java.math.BigDecimal;

import static com.bestseller.assignment.starbux.domainentitiy.ProductType.DRINK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ProductController Integration tests
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductIntegrationTest {

    private ProductService productService;

    @Autowired
    public ProductIntegrationTest(ProductService productService) {
        this.productService = productService;
    }

    @Test
    @DisplayName("Tests creating a product/topping")
    @Order(1)
    public void createProductTest() {
        Product americano = new Product("Americano", DRINK.ordinal(), BigDecimal.valueOf(1l));
        Product savedObject = productService.save(americano);
        assertEquals(americano.getProductType(), savedObject.getProductType());
        assertEquals(americano.getPrice(), savedObject.getPrice());
        assertEquals(americano.getName(), savedObject.getName());
        assertEquals(americano.getId(), savedObject.getId());
    }

    @Test
    @DisplayName("Tests creating a product price is null")
    @Order(2)
    public void createProductTestPriceIsNull() {
        Product americano = new Product("Mocha", DRINK.ordinal(), null);
        assertThrows(DataIntegrityViolationException.class, () -> productService.save(americano));
    }

    @Test
    @DisplayName("Tests creating a product which is repeated")
    @Order(3)
    public void createProductTestIsRepeated() {
        Product americano = new Product("Americano", DRINK.ordinal(), BigDecimal.valueOf(1l));
        assertThrows(DataIntegrityViolationException.class, () -> productService.save(americano));
    }

    @Test
    @DisplayName("Tests creating a product price is repeated")
    @Order(7)
    public void createProductTestNameIsEmpty() {
        Product americano = new Product("", DRINK.ordinal(), BigDecimal.valueOf(1l));
        assertThrows(TransactionSystemException.class, () -> productService.save(americano));
    }

    @Test
    @DisplayName("Tests deleting a product/topping")
    @Order(6)
    public void deleteProductTest() {
        Product americano = productService.deleteByName("Americano");
        assertNotNull(americano);
        assertThrows(ProductNotFoundException.class, () -> productService.findByName("Americano"));
    }

    @Test
    @DisplayName("Tests updating a product/topping")
    @Order(4)
    public void updateProductTest() {
        Product foundObject = productService.findByName("Americano");
        Product americano = new Product("Americano", DRINK.ordinal(), BigDecimal.valueOf(2l));
        Product updatedObject = productService.update(americano);
        assertEquals(americano.getProductType(), updatedObject.getProductType());
        assertEquals(americano.getPrice(), updatedObject.getPrice());
        assertNotEquals(foundObject.getPrice(), updatedObject.getPrice());
        assertEquals(americano.getName(), updatedObject.getName());
        assertEquals(americano.getId(), updatedObject.getId());
    }

    @Test
    @DisplayName("Tests getting a product/topping")
    @Order(5)
    public void getProductTest() {
        Product americano = productService.findByName("Americano");
        assertNotNull(americano);
    }
}
