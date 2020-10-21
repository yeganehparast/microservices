package com.bestseller.assignment.starbux.converter;

import com.bestseller.assignment.starbux.domainentitiy.Cart;
import com.bestseller.assignment.starbux.domainentitiy.OrderItem;
import com.bestseller.assignment.starbux.dto.CartDTO;
import com.bestseller.assignment.starbux.dto.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * This class converts different cart objects from the dto to domain object and vice versa.
 */
public class CartConverter {

    private ProductConverter productConverter = new ProductConverter();

    public CartDTO toResource(Cart cart) {
        return CartDTO.builder().
                clientName(cart.getClientName()).amount(cart.getAmount()).
                discount(cart.getDiscount()).
                total(cart.getTotal()).
                items(getOrderItemDTOs(cart.getItems())).
                build();
    }

    public Cart toModel(CartDTO cartDTO) {
        Cart cart = new Cart(cartDTO.getClientName(), cartDTO.getTotal(), cartDTO.getDiscount(), cartDTO.getAmount(), null);
        cart.setItems(getOrderItems(cartDTO.getItems(), cart));
        return cart;
    }

    public List<CartDTO> toResourceList(List<Cart> carts) {
        ArrayList<CartDTO> result = new ArrayList<>();
        carts.forEach(cart -> result.add(toResource(cart)));
        return result;
    }

    private List<OrderItemDTO> getOrderItemDTOs(List<OrderItem> orderItems) {
        ArrayList<OrderItemDTO> result = new ArrayList<>();
        orderItems.forEach(orderItem -> result.add(toResource(orderItem)));
        return result;
    }

    private List<OrderItem> getOrderItems(List<OrderItemDTO> orderItemDTOs, Cart cart) {
        ArrayList<OrderItem> result = new ArrayList<>();
        orderItemDTOs.forEach(dto -> result.add(toModel(dto, cart)));
        return result;
    }

    public OrderItemDTO toResource(OrderItem orderItem) {
        return OrderItemDTO.builder().
                productDTO(productConverter.toResource(orderItem.getProduct())).
                build();
    }

    public OrderItem toModel(OrderItemDTO orderItemDTO, Cart cart) {
        return new OrderItem(cart, productConverter.toModel(orderItemDTO.getProductDTO()));
    }
}
