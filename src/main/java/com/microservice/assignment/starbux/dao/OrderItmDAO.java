package com.bestseller.assignment.starbux.dao;

import com.bestseller.assignment.starbux.domainentitiy.OrderItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Representing the OrderItemDAO repository interface
 */
public interface OrderItmDAO extends CrudRepository<OrderItem, Long> {
}
