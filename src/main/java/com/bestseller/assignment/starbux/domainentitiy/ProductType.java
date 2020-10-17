package com.bestseller.assignment.starbux.domainentitiy;

/**
 * This Enum is purposely defined to keep the type of products.
 */

public enum ProductType {
    /**
     * If type of product is a drink
     */
    DRINK("drink"),
    /**
     * if the type of product is a topping
     */
    TOPPING("topping");

    private String type;

    ProductType (String type) {
        this.type = type;
    }
}
