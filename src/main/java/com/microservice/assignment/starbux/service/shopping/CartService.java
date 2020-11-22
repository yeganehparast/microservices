package com.microservice.assignment.starbux.service.shopping;

import com.microservice.assignment.starbux.domainentitiy.Cart;
import com.microservice.assignment.starbux.domainentitiy.Product;
import com.microservice.assignment.starbux.service.exception.OrderItemNotFoundException;
import com.microservice.assignment.starbux.service.exception.ToppingNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;

/**
 * This interface defines the businesses for Cart
 */
public interface CartService {

    Cart save(Cart cart) throws DataIntegrityViolationException, TransactionSystemException, InvalidDataAccessApiUsageException, OrderItemNotFoundException;

    List<Cart> getAmountForCustomer(String name);

    Product getPopularTopping() throws ToppingNotFoundException;

    void deleteAll();
}
