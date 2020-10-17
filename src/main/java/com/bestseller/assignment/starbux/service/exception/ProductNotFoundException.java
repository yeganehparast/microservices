package com.bestseller.assignment.starbux.service.exception;

import com.bestseller.assignment.starbux.exception.StarbuxException;

/**
 * ProductNotFoundException will be thrown when Product is not found
 */
public class ProductNotFoundException extends StarbuxException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
