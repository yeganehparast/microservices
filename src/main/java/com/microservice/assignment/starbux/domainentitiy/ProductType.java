package com.microservice.assignment.starbux.domainentitiy;

/**
 * This Enum is purposely defined to keep the type of products.
 */

public enum ProductType {
    /**
     * If type of product is a drink
     */
    DRINK(0, "drink"),
    /**
     * if the type of product is a topping
     */
    TOPPING(1, "topping");

    private String type;
    private int code;

    ProductType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public static String getNameType(int code) {
        switch (code) {
            case 1:
                return "topping";
            default:
                return "drink";
        }
    }

    public static int getCode(String type) {
        switch (type) {
            case "topping":
                return 1;
            default:
                return 0;
        }
    }

}
