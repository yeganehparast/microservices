package com.microservice.assignment.starbux.service.product;

import com.microservice.assignment.starbux.domainentitiy.Product;
import com.microservice.assignment.starbux.domainentitiy.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * Mocking tests of CRUD methods for Product Entity
 */
@ExtendWith(MockitoExtension.class)
class ProductUnitTest {

    private ProductService productService;

    @BeforeEach
    void init(@Mock ProductService productService) {
        this.productService = productService;
    }

    @Test
    @DisplayName("Tests creating a product")
    void createDrinkTest() {
        //create a drink
        long idAmericano = 12L;
        when(productService.save(any(Product.class))).thenAnswer((Answer<Product>) invocationOnMock -> {
            Product product = invocationOnMock.getArgument(0);
            product.setId(idAmericano);
            return product;
        });
        Product americano = new Product("Americano", ProductType.DRINK.ordinal(), BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_DOWN));
        Product savedProductDrink = productService.save(americano);
        assertNotNull(savedProductDrink);
        assertEquals(idAmericano, savedProductDrink.getId());
        assertEquals(americano.getPrice(), savedProductDrink.getPrice());
    }

    @Test
    @DisplayName("Tests creating a topping")
    void createToppingTest() {
        //create a topping
        long idCaramelSyrup = 14L;
        when(productService.save(any(Product.class))).thenAnswer((Answer<Product>) invocationOnMock -> {
            Product product = invocationOnMock.getArgument(0);
            product.setId(idCaramelSyrup);
            return product;
        });
        Product sampleProduct = new Product("Caramel Syrup", ProductType.DRINK.ordinal(), BigDecimal.valueOf(2).setScale(2, RoundingMode.HALF_DOWN));
        Product savedProductTopping = productService.save(sampleProduct);
        assertNotNull(savedProductTopping);
        assertEquals(idCaramelSyrup, savedProductTopping.getId());
        assertEquals(sampleProduct.getPrice(), savedProductTopping.getPrice());
    }
}
