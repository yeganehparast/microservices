package com.microservice.assignment.starbux.dao;

import com.microservice.assignment.starbux.domainentitiy.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Representing ShoppingCartDAO repository interface
 */
public interface CartDAO extends CrudRepository<Cart, Long> {

    List<Cart> getCartsByClientName(String name);
}
