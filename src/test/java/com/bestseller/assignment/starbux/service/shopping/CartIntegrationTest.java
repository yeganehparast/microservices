package com.bestseller.assignment.starbux.service.shopping;

import com.bestseller.assignment.starbux.domainentitiy.Cart;
import com.bestseller.assignment.starbux.domainentitiy.OrderItem;
import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.domainentitiy.ProductType;
import com.bestseller.assignment.starbux.service.product.ProductService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bestseller.assignment.starbux.TestUtils.*;
import static java.math.RoundingMode.HALF_DOWN;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Cart Controller Integration Tests
 */
@SpringBootTest
public class CartIntegrationTest {

    private CartService cartService;
    private ProductService productService;

    @Autowired
    CartIntegrationTest(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @Test
    @DisplayName("Testing creating a shopping cart when products are not saved")
    void createShoppingCartProductsAreNotSavedTest() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> cartService.save(getCart(true, false, 1, 12)));
    }

    @Test
    @DisplayName("Testing creating a shopping cart when products are saved first")
    void createShoppingCartProductsSavedTest() {
        Cart save = cartService.save(getCart(true, true, 1, 12));
        assertNotNull(save);
        assertNotNull(save.getId());
        assertNotNull(save.getItems());
        assertNotNull(save.getClientName());
        assertNotNull(save.getAmount());
        assertNotNull(save.getDiscount());
        assertNotNull(save.getTotal());
        save.getItems().forEach(orderItem -> {
            assertNotNull(orderItem.getId());
            assertNotNull(orderItem.getProduct());
            assertNotNull(orderItem.getProduct().getPrice());
            assertNotNull(orderItem.getProduct().getProductType());
            assertNotNull(orderItem.getProduct().getName());
            assertNotNull(orderItem.getProduct().getId());
            assertNotNull(orderItem.getCart());
            assertEquals(save, orderItem.getCart());
        });
    }

    @Test
    @DisplayName("Testing discount logic when total amount is bigger than 12")
    void testCalculation() {
        Cart save = cartService.save(getCart(true, true, 6, 20));
        BigDecimal sum = new BigDecimal(0);
        for (OrderItem orderItem : save.getItems()) {
            sum = sum.add(orderItem.getProduct().getPrice());
        }
        assertEquals(0, save.getAmount().compareTo(sum));
        assertEquals(0, save.getAmount().subtract(save.getDiscount()).compareTo(save.getTotal()));
    }

    @Test
    @DisplayName("Testing find the popular topping")
    void testFindingPopularTopping() {
        for (int i = 0; i < 10; i++) {
            cartService.save(getCart(false, true, 2, 10));
        }
        Product popularTopping = cartService.getPopularTopping();
        assertNotNull(popularTopping);
        assertEquals(popularTopping.getProductType(), ProductType.TOPPING.ordinal());
    }

    @Test
    @DisplayName("Testing getting amount per customer")
    void testAmountPerCustomer() {
        Cart save = cartService.save(getCart(false, true, 2, 10));
        List<Cart> amountForCustomer = cartService.getAmountForCustomer(save.getClientName());

        assertEquals(1, amountForCustomer.size());
        assertEquals(amountForCustomer.get(0).getClientName(), save.getClientName());
        assertNotNull(amountForCustomer.get(0).getAmount());
        assertNotNull(amountForCustomer.get(0).getTotal());
        assertNotNull(amountForCustomer.get(0).getDiscount());
    }

    private Cart getCart(boolean deleteBefore, boolean saveProduct, int orderItemSize, double sum) {
        if (deleteBefore) {
            cartService.deleteAll();
            productService.deleteAll();
        }
        BigDecimal defaultValue = new BigDecimal(0).setScale(2, HALF_DOWN);
        String name = RandomStringUtils.randomAlphabetic(5);
        Map<String, Product> productMap = getProductMap();
        if (saveProduct) {
            productMap.values().stream().forEach(product -> {
                boolean exists = productService.existsByName(product.getName());
                if (saveProduct && !exists) {
                    product = productService.save(product);
                } else {
                    product = exists ? productService.findByName(product.getName()) : product;
                }
                productMap.put(product.getName(), product);
            });
        }
        Cart cart = new Cart(name, defaultValue, defaultValue, defaultValue, new ArrayList<>());
        double total = 0;
        for (int i = 0; i < orderItemSize && total < sum; i++) {
            Product drink = productMap.get(drinks.get((int) getRandomPrice(0, 4)));
            Product topping = productMap.get(toppings.get((int) getRandomPrice(0, 4)));
            cart.getItems().add(new OrderItem(cart, drink));
            cart.getItems().add(new OrderItem(cart, topping));
            total = (BigDecimal.valueOf(total).add(drink.getPrice().add(topping.getPrice()))).doubleValue();
        }
        return cart;
    }

}
