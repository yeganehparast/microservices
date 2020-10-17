package com.bestseller.assignment.starbux.domainentitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * This is an Entity to keep the product information
 */

@Entity(name = "Product")
@Table(name = "Product")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    private BigDecimal price;
}
