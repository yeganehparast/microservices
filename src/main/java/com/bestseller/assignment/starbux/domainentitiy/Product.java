package com.bestseller.assignment.starbux.domainentitiy;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * This is an Entity to keep the product information
 */

@Entity(name = "Product")
@Table(name = "Product")
@ToString
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Name should be filled")
    private String name;

    @Column(nullable = false)
    private int productType;

    @Column(nullable = false)
    private BigDecimal price;


    public Product(String name, int productType, BigDecimal price) {
        this.name = name;
        this.productType = productType;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
