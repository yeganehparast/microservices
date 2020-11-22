package com.microservice.assignment.starbux.domainentitiy;

import javax.persistence.*;

/**
 * This is the class where keeps the order items of a shopping cart
 */

@Entity(name = "OrderItem")
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    //@NotNull
    private Cart cart;

    @ManyToOne
    //@NotNull
    private Product product;

    public OrderItem(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
    }

    public OrderItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
