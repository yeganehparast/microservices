package com.microservice.assignment.starbux.dao;

import com.microservice.assignment.starbux.domainentitiy.OrderItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Representing the OrderItemDAO repository interface
 */
public interface OrderItmDAO extends CrudRepository<OrderItem, Long> {
}
