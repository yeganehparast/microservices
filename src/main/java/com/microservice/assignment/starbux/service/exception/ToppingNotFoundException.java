package com.bestseller.assignment.starbux.service.exception;

import com.bestseller.assignment.starbux.exception.StarbuxException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToppingNotFoundException extends StarbuxException {

    public ToppingNotFoundException(String message) {
        super(message);
        log.error("Topping not found");
    }
}
