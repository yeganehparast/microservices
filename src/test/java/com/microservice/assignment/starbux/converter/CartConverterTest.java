package com.bestseller.assignment.starbux.converter;

import com.bestseller.assignment.starbux.domainentitiy.Cart;
import com.bestseller.assignment.starbux.domainentitiy.OrderItem;
import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.domainentitiy.ProductType;
import com.bestseller.assignment.starbux.dto.CartDTO;
import com.bestseller.assignment.starbux.dto.OrderItemDTO;
import com.bestseller.assignment.starbux.dto.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CartConverterTest {

    CartConverter cartConverter = new CartConverter();

    @DisplayName("Test converting a Cart from Model to resource")
    @Test
    public void toResourceCartTest() {
        Cart cart = new Cart("Jim", BigDecimal.valueOf(2l), BigDecimal.valueOf(.5), BigDecimal.valueOf(1.5), new ArrayList<>());
        Product americano = new Product("Americano", ProductType.DRINK.ordinal(), BigDecimal.valueOf(2l));
        Product caramel = new Product("Caramel", ProductType.TOPPING.ordinal(), BigDecimal.valueOf(1l));
        cart.getItems().add(new OrderItem(cart, americano));
        cart.getItems().add(new OrderItem(cart, caramel));
        CartDTO cartDTO = cartConverter.toResource(cart);

        assertEquals(cart.getClientName(), cartDTO.getClientName());
        assertEquals(cart.getTotal(), cartDTO.getTotal());
        assertEquals(cart.getDiscount(), cartDTO.getDiscount());
        assertEquals(cart.getAmount(), cartDTO.getAmount());
        assertFalse(cartDTO.getItems().isEmpty());
        assertEquals(cart.getItems().size(), cartDTO.getItems().size());
        for (int i = 0; i < cartDTO.getItems().size(); i++) {
            assertEquals(cart.getItems().get(i).getProduct().getName(), cartDTO.getItems().get(i).getProductDTO().getName());
            assertEquals(cart.getItems().get(i).getProduct().getPrice(), cartDTO.getItems().get(i).getProductDTO().getPrice());
            assertEquals(ProductType.getNameType(cart.getItems().get(i).getProduct().getProductType()), cartDTO.getItems().get(i).getProductDTO().getProductType());
        }
    }

    @DisplayName("Test converting a CartDTO from resource to model")
    @Test
    public void toModelCartTest() {
        CartDTO jimCartDTO = CartDTO.builder().clientName("Jim").total(BigDecimal.valueOf(2l)).discount(BigDecimal.valueOf(.5)).amount(BigDecimal.valueOf(1.5)).items(new ArrayList<>()).build();
        ProductDTO americano = ProductDTO.builder().name("Americano").productType("drink").price(BigDecimal.valueOf(2l)).build();
        ProductDTO caramel = ProductDTO.builder().name("caramel").productType("topping").price(BigDecimal.valueOf(1l)).build();
        jimCartDTO.getItems().add(OrderItemDTO.builder().productDTO(americano).build());
        jimCartDTO.getItems().add(OrderItemDTO.builder().productDTO(caramel).build());
        Cart cart = cartConverter.toModel(jimCartDTO);

        assertEquals(jimCartDTO.getClientName(), cart.getClientName());
        assertEquals(jimCartDTO.getTotal(), cart.getTotal());
        assertEquals(jimCartDTO.getDiscount(), cart.getDiscount());
        assertEquals(jimCartDTO.getAmount(), cart.getAmount());
        assertFalse(cart.getItems().isEmpty());
        assertEquals(jimCartDTO.getItems().size(), cart.getItems().size());
        for (int i = 0; i < jimCartDTO.getItems().size(); i++) {
            assertEquals(jimCartDTO.getItems().get(i).getProductDTO().getName(), cart.getItems().get(i).getProduct().getName());
            assertEquals(jimCartDTO.getItems().get(i).getProductDTO().getPrice(), cart.getItems().get(i).getProduct().getPrice());
            assertEquals(ProductType.getCode(jimCartDTO.getItems().get(i).getProductDTO().getProductType()), cart.getItems().get(i).getProduct().getProductType());
        }
    }
}
