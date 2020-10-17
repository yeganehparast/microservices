package com.bestseller.assignment.starbux.service.shopping;

import com.bestseller.assignment.starbux.domainentitiy.Cart;
import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.service.exception.CartNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the implementation of the CartService
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public Cart get(Cart cart) throws CartNotFoundException {
        return null;
    }

    @Override
    public boolean isDiscountable(Cart cart) {
        return false;
    }

    @Override
    public List<Cart> getAmountForCustomer(String name) {
        return null;
    }

    @Override
    public Product getPopularTopping() {
        return null;
    }
}
