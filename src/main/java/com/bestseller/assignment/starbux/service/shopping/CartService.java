package com.bestseller.assignment.starbux.service.shopping;

import com.bestseller.assignment.starbux.domainentitiy.Cart;
import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.service.exception.CartNotFoundException;

import java.util.List;

/**
 * This interface defines the businesses for Cart
 */
public interface CartService {

    Cart save(Cart cart);

    Cart get(Cart cart) throws CartNotFoundException;

    boolean isDiscountable(Cart cart);

    List<Cart> getAmountForCustomer(String name);

    Product getPopularTopping();
}
