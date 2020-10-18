package com.bestseller.assignment.starbux.domainentitiy;

import lombok.*;

import javax.persistence.*;

/**
 * This is the class where keeps the order items of a shopping cart
 */

@Entity(name = "OrderItem")
@Table(name = "OrderItem")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private int count;
}
