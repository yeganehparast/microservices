package com.bestseller.assignment.starbux.service.product;

import com.bestseller.assignment.starbux.aspect.LogMehtod;
import com.bestseller.assignment.starbux.dao.ProductDAO;
import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.service.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

/**
 * This is the implementation class for ProductServiceImpl
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @LogMehtod
    public Product save(Product product) throws DataIntegrityViolationException, TransactionSystemException {
        try {
            log.info(String.format("Product %s is being saved", product.getName()));
            return productDAO.save(product);
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Input data cannot be integrated.\n%s", product.toString()));
            throw new DataIntegrityViolationException(String.format("Input data cannot be integrated.\n%s", product.toString()));
        } catch (TransactionSystemException e) {
            log.error(String.format("Input data is inconsistent.\n%s", product.toString()));
            throw new TransactionSystemException(String.format("Input data is inconsistent.\n%s", product.toString()));
        }
    }

    @Override
    @LogMehtod
    public Product update(Product product) throws ConstraintViolationException {
        log.info(String.format("Product %s is being updated", product.getName()));
        Product dbObject = findByName(product.getName());
        product.setId(dbObject.getId());
        return productDAO.save(product);
    }

    @Override
    @LogMehtod
    public Product findByName(String name) throws ProductNotFoundException {
        log.info(String.format("Product %s is being found", name));
        return productDAO.findByName(name).orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    @Override
    @LogMehtod
    public Product deleteByName(String name) throws ProductNotFoundException {
        log.info(String.format("Product %s is being found", name));
        Product product = productDAO.findByName(name).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        productDAO.delete(product);
        return product;
    }

    @Override
    public void deleteAll() {
        productDAO.deleteAll();
    }

    @Override
    public boolean existsByName(String name) {
        return productDAO.existsByName(name);
    }
}
