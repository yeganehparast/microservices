package com.bestseller.assignment.starbux.service.exception;

import com.bestseller.assignment.starbux.exception.StarbuxException;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderItemNotFoundException will be thrown when OrderItem is not found
 */
@Slf4j
public class OrderItemNotFoundException extends StarbuxException {

    public OrderItemNotFoundException(String message) {
        super(message);
        log.error("OrderItem not found");

    }
}
