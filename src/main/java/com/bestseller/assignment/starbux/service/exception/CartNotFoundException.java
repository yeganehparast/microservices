package com.bestseller.assignment.starbux.service.exception;

import com.bestseller.assignment.starbux.exception.StarbuxException;

/**
 * CartNotFoundException will be thrown when cart is not found
 */
public class CartNotFoundException extends StarbuxException {

    public CartNotFoundException(String message) {
        super(message);
    }
}
