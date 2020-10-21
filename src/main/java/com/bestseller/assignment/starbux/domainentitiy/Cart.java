package com.bestseller.assignment.starbux.domainentitiy;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

/**
 * This is an Entity class to keep the shopping for specifically for shopping of a customer
 */
@Entity(name = "shopping_cart")
@Table(name = "shopping_cart")
@ToString
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name should not be blank")
    private String clientName;

    private BigDecimal total;

    private BigDecimal discount;

    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(nullable = false)
    private List<OrderItem> items;

    public Cart(String clientName, BigDecimal total, BigDecimal discount, BigDecimal amount, List<OrderItem> items) {
        this.clientName = clientName;
        this.total = total;
        this.discount = discount;
        this.amount = amount;
        this.items = items;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
