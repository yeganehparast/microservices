package com.bestseller.assignment.starbux.service.shopping;

import com.bestseller.assignment.starbux.domainentitiy.OrderItem;
import com.bestseller.assignment.starbux.service.exception.OrderItemNotFoundException;

/**
 * This interfaces handles the required business for the OrderItems
 */
public interface OrderItemService {

    OrderItem save(OrderItem orderItem);

    OrderItem get(Long orderItemId) throws OrderItemNotFoundException;

    OrderItem getByCartId(Long cardId);
}
