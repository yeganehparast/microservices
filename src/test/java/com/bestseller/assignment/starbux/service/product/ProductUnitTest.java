package com.bestseller.assignment.starbux.service.product;

import com.bestseller.assignment.starbux.dao.ProductDAO;
import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.domainentitiy.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Mocking tests of CRUD methods for Product Entity
 */
@ExtendWith(MockitoExtension.class)
public class ProductUnitTest {

    private ProductDAO productDAO;

    @BeforeEach
    public void init(@Mock ProductDAO productDAO) {
        this.productDAO = productDAO;

        lenient().doAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]).
                when(productDAO).save(any(Product.class));

        lenient().doAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]).
                when(productDAO).delete(any(Product.class));

        lenient().doAnswer(invocationOnMock -> invocationOnMock.getArguments()[0]).
                when(productDAO).existsById(anyLong());
    }

    @Test
    @DisplayName("Tests creating a product/topping")
    public void createProductTest() {
        Product americano = buildProduct(1L, ProductType.DRINK, 1, "Americano");

        Product caramelSyrup = buildProduct(1L, ProductType.TOPPING, .2, "Caramel Syrup");

        Product americanoMocked = productDAO.save(americano);
        assertNotNull(americanoMocked);

        Product caramelSyrupMocked = productDAO.save(caramelSyrup);
        assertNotNull(caramelSyrupMocked);
    }

    @Test
    @DisplayName("Tests deleting a product/topping")
    public void deleteProductTest() {
        Product americano = buildProduct(1L, ProductType.DRINK, 1, "Americano");

        Product americanoMocked = productDAO.save(americano);
        assertNotNull(productDAO.save(americano));

        productDAO.delete(americanoMocked);

        System.out.println(productDAO.existsById(americano.getId()));
    }

    @Test
    @DisplayName("Tests updating a product/topping")
    public void updateProductTest() {

    }

    @Test
    @DisplayName("Tests getting a product/topping")
    public void getProductTest() {

    }

    private Product buildProduct(Long id, ProductType productType, double price, String name) {
        return Product.builder().
                price(BigDecimal.valueOf(price).setScale(2)).
                productType(productType).
                name(name).
                id((long) id).
                build();
    }
}
