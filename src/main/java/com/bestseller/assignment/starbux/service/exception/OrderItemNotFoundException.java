package com.bestseller.assignment.starbux.service.exception;

import com.bestseller.assignment.starbux.exception.StarbuxException;

/**
 * OrderItemNotFoundException will be thrown when OrderItem is not found
 */
public class OrderItemNotFoundException extends StarbuxException {

    public OrderItemNotFoundException(String message) {
        super(message);
    }
}
