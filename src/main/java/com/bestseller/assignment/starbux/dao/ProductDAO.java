package com.bestseller.assignment.starbux.dao;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Representing the ProductDAO repository interface
 */
public interface ProductDAO extends CrudRepository<Product, Long> {
}
