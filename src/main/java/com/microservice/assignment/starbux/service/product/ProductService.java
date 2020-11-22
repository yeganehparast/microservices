package com.bestseller.assignment.starbux.service.product;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.TransactionSystemException;

/**
 * This interface defines the business methods for Product
 */
public interface ProductService {

    Product save(Product product) throws DataIntegrityViolationException, TransactionSystemException;

    Product update(Product product) throws DataIntegrityViolationException, TransactionSystemException;

    Product findByName(String name);

    Product deleteByName(String name);

    void deleteAll();

    boolean existsByName(String name);
}
