package com.bestseller.assignment.starbux.domainentitiy;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This is an Entity class to keep the shopping for specifically for shopping of a customer
 */
@Entity(name = "shopping_cart")
@Table(name = "shopping_cart")
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private LocalDateTime localDateTime;

    private String clientName;

    private BigDecimal total;

    private BigDecimal discount;

    private BigDecimal amount;
}
