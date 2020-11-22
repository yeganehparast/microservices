package com.microservice.assignment.starbux.service.shopping;

import com.microservice.assignment.starbux.TestUtils;
import com.microservice.assignment.starbux.dao.CartDAO;
import com.microservice.assignment.starbux.dao.OrderItmDAO;
import com.microservice.assignment.starbux.dao.ProductDAO;
import com.microservice.assignment.starbux.domainentitiy.Cart;
import com.microservice.assignment.starbux.domainentitiy.OrderItem;
import com.microservice.assignment.starbux.domainentitiy.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Mocking test for calculation of amounts and discounts
 */
@RunWith(PowerMockRunner.class)
public class CartUnitTest {

    private CartDAO cartDAO;
    private OrderItmDAO orderItmDAO;
    private ProductDAO productDAO;

    @Before
    public void init() {
        this.cartDAO = mock(CartDAO.class);
        this.orderItmDAO = mock(OrderItmDAO.class);
        this.productDAO = mock(ProductDAO.class);
    }

    @Test
    public void tes_calculateTicket_TOTAL_BIGGER_THAN_TWELVE() throws Exception {
        CartServiceImpl mock = spy(new CartServiceImpl(cartDAO, orderItmDAO, productDAO));
        Cart cart = getCartTotalAmountBiggerThanTwelve();
        Cart calculateTicket = Whitebox.invokeMethod(mock, "calculateTicket", cart);
        assertNotNull(calculateTicket);
        assertNotNull(calculateTicket.getAmount());
        assertNotNull(calculateTicket.getDiscount());
        assertNotNull(calculateTicket.getTotal());
        BigDecimal amount = TestUtils.getPriceFromDouble(12.5, 3);
        //25%
        BigDecimal discount = TestUtils.getPriceFromDouble(3.125, 3);
        BigDecimal total = TestUtils.getPriceFromDouble(12.5 - 3.125, 3);

        assertEquals(0, amount.compareTo(calculateTicket.getAmount()));
        assertEquals(0, discount.compareTo(calculateTicket.getDiscount()));
        assertEquals(0, total.compareTo(calculateTicket.getTotal()));
    }

    @Test
    public void tes_calculateTicket_THERE_DRINKS() throws Exception {
        CartServiceImpl mock = spy(new CartServiceImpl(cartDAO, orderItmDAO, productDAO));
        Cart cartWithThreeDrinks = getCartWithThreeDrinks();
        Cart calculateTicket = Whitebox.invokeMethod(mock, "calculateTicket", cartWithThreeDrinks);
        assertNotNull(calculateTicket);
        assertNotNull(calculateTicket.getAmount());
        assertNotNull(calculateTicket.getDiscount());
        assertNotNull(calculateTicket.getTotal());
        BigDecimal amount = TestUtils.getPriceFromDouble(10.5, 3);
        //LOWEST DRINK AND TOPPING
        BigDecimal discount = TestUtils.getPriceFromDouble(2.5, 3);
        BigDecimal total = TestUtils.getPriceFromDouble(10.5 - 2.5, 3);

        assertEquals(0, amount.compareTo(calculateTicket.getAmount()));
        assertEquals(0, discount.compareTo(calculateTicket.getDiscount()));
        assertEquals(0, total.compareTo(calculateTicket.getTotal()));
    }

    @Test
    public void tes_calculateTicket_EQUAL_CONDITIONS_DRINKS() throws Exception {
        CartServiceImpl mock = spy(new CartServiceImpl(cartDAO, orderItmDAO, productDAO));
        Cart cartWithThreeDrinks = getCartEqualConditions();
        Cart calculateTicket = Whitebox.invokeMethod(mock, "calculateTicket", cartWithThreeDrinks);
        assertNotNull(calculateTicket);
        assertNotNull(calculateTicket.getAmount());
        assertNotNull(calculateTicket.getDiscount());
        assertNotNull(calculateTicket.getTotal());
        BigDecimal amount = TestUtils.getPriceFromDouble(12.5, 3);
        //LOWEST DRINK AND TOPPING
        BigDecimal discount = TestUtils.getPriceFromDouble(1.5, 3);
        BigDecimal total = TestUtils.getPriceFromDouble(12.5 - 1.5, 3);

        assertEquals(0, amount.compareTo(calculateTicket.getAmount()));
        assertEquals(0, discount.compareTo(calculateTicket.getDiscount()));
        assertEquals(0, total.compareTo(calculateTicket.getTotal()));
    }

    private Cart getCartTotalAmountBiggerThanTwelve() {
        Map<String, Product> productMap = TestUtils.getProductMap();
        productMap.get("cappuccino").setPrice(TestUtils.getPriceFromDouble(5));
        productMap.get("espresso").setPrice(TestUtils.getPriceFromDouble(3));
        productMap.get("cream").setPrice(TestUtils.getPriceFromDouble(2.5));
        productMap.get("caramel").setPrice(TestUtils.getPriceFromDouble(2));

        Cart cart = new Cart("Client1", TestUtils.getPriceFromDouble(0), TestUtils.getPriceFromDouble(0), TestUtils.getPriceFromDouble(0), new ArrayList<>());
        cart.getItems().add(new OrderItem(cart, productMap.get("cappuccino")));
        cart.getItems().add(new OrderItem(cart, productMap.get("espresso")));
        cart.getItems().add(new OrderItem(cart, productMap.get("cream")));
        cart.getItems().add(new OrderItem(cart, productMap.get("caramel")));
        return cart;
    }

    private Cart getCartWithThreeDrinks() {
        Map<String, Product> productMap = TestUtils.getProductMap();
        productMap.get("cappuccino").setPrice(TestUtils.getPriceFromDouble(3));
        productMap.get("espresso").setPrice(TestUtils.getPriceFromDouble(2));
        productMap.get("tea").setPrice(TestUtils.getPriceFromDouble(2));
        productMap.get("caramel").setPrice(TestUtils.getPriceFromDouble(1));
        productMap.get("lemon").setPrice(TestUtils.getPriceFromDouble(0.5));

        Cart cart = new Cart("Client1", TestUtils.getPriceFromDouble(0), TestUtils.getPriceFromDouble(0), TestUtils.getPriceFromDouble(0), new ArrayList<>());
        cart.getItems().add(new OrderItem(cart, productMap.get("cappuccino")));
        cart.getItems().add(new OrderItem(cart, productMap.get("espresso")));
        cart.getItems().add(new OrderItem(cart, productMap.get("tea")));
        cart.getItems().add(new OrderItem(cart, productMap.get("caramel")));
        cart.getItems().add(new OrderItem(cart, productMap.get("espresso")));
        cart.getItems().add(new OrderItem(cart, productMap.get("lemon")));

        return cart;
    }

    private Cart getCartEqualConditions() {
        Map<String, Product> productMap = TestUtils.getProductMap();
        productMap.get("cappuccino").setPrice(TestUtils.getPriceFromDouble(3));
        productMap.get("espresso").setPrice(TestUtils.getPriceFromDouble(2));
        productMap.get("tea").setPrice(TestUtils.getPriceFromDouble(1));
        productMap.get("caramel").setPrice(TestUtils.getPriceFromDouble(1));
        productMap.get("lemon").setPrice(TestUtils.getPriceFromDouble(0.5));

        Cart cart = new Cart("Client1", TestUtils.getPriceFromDouble(0), TestUtils.getPriceFromDouble(0), TestUtils.getPriceFromDouble(0), new ArrayList<>());
        cart.getItems().add(new OrderItem(cart, productMap.get("cappuccino")));
        cart.getItems().add(new OrderItem(cart, productMap.get("cappuccino")));
        cart.getItems().add(new OrderItem(cart, productMap.get("espresso")));
        cart.getItems().add(new OrderItem(cart, productMap.get("tea")));
        cart.getItems().add(new OrderItem(cart, productMap.get("caramel")));
        cart.getItems().add(new OrderItem(cart, productMap.get("espresso")));
        cart.getItems().add(new OrderItem(cart, productMap.get("lemon")));

        return cart;
    }

}
