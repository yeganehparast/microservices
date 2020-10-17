package com.bestseller.assignment.starbux.service.product;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.service.exception.ProductNotFoundException;

/**
 * This interface defines the business methods for Product
 */
public interface ProductService {

    Product save(Product product);

    Product update(Product product);

    Product get(Long productId) throws ProductNotFoundException;

    void delete(Long productId);
}
