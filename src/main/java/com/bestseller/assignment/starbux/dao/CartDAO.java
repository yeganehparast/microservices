package com.bestseller.assignment.starbux.dao;

import com.bestseller.assignment.starbux.domainentitiy.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * Representing ShoppingCartDAO repository interface
 */
public interface CartDAO extends CrudRepository<Cart, Long> {
}
