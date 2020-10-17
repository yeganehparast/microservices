package com.bestseller.assignment.starbux.service.shopping;

import com.bestseller.assignment.starbux.domainentitiy.OrderItem;
import com.bestseller.assignment.starbux.service.exception.OrderItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * This class is the implementation of the OrderItemService
 */
@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public OrderItem save(OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem get(Long orderItemId) throws OrderItemNotFoundException {
        return null;
    }

    @Override
    public OrderItem getByCartId(Long cardId) {
        return null;
    }
}
