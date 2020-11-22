package com.bestseller.assignment.starbux.dao;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Representing the ProductDAO repository interface
 */
public interface ProductDAO extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String name);

    boolean existsByName(String name);
}
