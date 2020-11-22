package com.microservice.assignment.starbux.service.exception;

import com.microservice.assignment.starbux.exception.StarbuxException;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductNotFoundException will be thrown when Product is not found
 */
@Slf4j
public class ProductNotFoundException extends StarbuxException {

    public ProductNotFoundException(String message) {
        super(message);
        log.error("Product not found");
    }
}
