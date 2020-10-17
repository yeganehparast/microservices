package com.bestseller.assignment.starbux.service.product;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This is the implementation class for ProductServiceImpl
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product get(Long productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void delete(Long productId) {
    }
}
