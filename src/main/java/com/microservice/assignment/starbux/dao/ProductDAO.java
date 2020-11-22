package com.microservice.assignment.starbux.dao;

import com.microservice.assignment.starbux.domainentitiy.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Representing the ProductDAO repository interface
 */
public interface ProductDAO extends CrudRepository<Product, Long> {

    Optional<Product> findByName(String name);

    boolean existsByName(String name);
}
