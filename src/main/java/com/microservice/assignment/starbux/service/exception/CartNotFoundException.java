package com.microservice.assignment.starbux.service.exception;

import com.microservice.assignment.starbux.exception.StarbuxException;
import lombok.extern.slf4j.Slf4j;

/**
 * CartNotFoundException will be thrown when cart is not found
 */
@Slf4j
public class CartNotFoundException extends StarbuxException {

    public CartNotFoundException(String message) {
        super(message);
        log.error("Cart not found");
    }
}
