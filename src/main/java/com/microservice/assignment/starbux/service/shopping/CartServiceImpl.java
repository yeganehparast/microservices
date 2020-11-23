package com.microservice.assignment.starbux.service.shopping;

import com.microservice.assignment.starbux.aspect.LogMehtod;
import com.microservice.assignment.starbux.dao.CartDAO;
import com.microservice.assignment.starbux.dao.OrderItmDAO;
import com.microservice.assignment.starbux.dao.ProductDAO;
import com.microservice.assignment.starbux.domainentitiy.Cart;
import com.microservice.assignment.starbux.domainentitiy.OrderItem;
import com.microservice.assignment.starbux.domainentitiy.Product;
import com.microservice.assignment.starbux.domainentitiy.ProductType;
import com.microservice.assignment.starbux.service.exception.CartNotFoundException;
import com.microservice.assignment.starbux.service.exception.OrderItemNotFoundException;
import com.microservice.assignment.starbux.service.exception.ToppingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.microservice.assignment.starbux.domainentitiy.ProductType.DRINK;
import static com.microservice.assignment.starbux.domainentitiy.ProductType.TOPPING;

/**
 * This class is the implementation of the CartService
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;
    private OrderItmDAO orderItmDAO;
    private ProductDAO productDAO;
    private final int discountBasedOnPricePercent = 12;
    private final int discountBasedOnCountOfDrinks = 3;

    public CartServiceImpl(CartDAO cartDAO, OrderItmDAO orderItmDAO, ProductDAO productDAO) {
        this.orderItmDAO = orderItmDAO;
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
    }

    @Override
    @LogMehtod
    public Cart save(Cart cart) throws OrderItemNotFoundException, DataIntegrityViolationException, TransactionSystemException, InvalidDataAccessApiUsageException {
        log.info(String.format("Cart for %s is being saved.", cart.getClientName()));
        try {
            if (cart.getItems() != null && !cart.getItems().isEmpty()) {
                cart.getItems().forEach(orderItem -> {
                    if (orderItem.getProduct().getId() == null && productDAO.existsByName(orderItem.getProduct().getName())) {
                        orderItem.setProduct(productDAO.findByName(orderItem.getProduct().getName()).get());
                    }
                });
                Cart cartReady = calculateTicket(cart);
                return cartDAO.save(cartReady);
            } else {
                log.error(String.format("Order items are empty. \n%s", cart.toString()));
                throw new OrderItemNotFoundException(String.format("Order items are empty. \n%s", cart));
            }
        } catch (DataIntegrityViolationException e) {
            log.error(String.format("Input data cannot be integrated.\n%s", cart.toString()));
            throw new DataIntegrityViolationException(String.format("Input data cannot be integrated.\n%s", cart));
        } catch (TransactionSystemException e) {
            log.error(String.format("Input data is inconsistent.\n%s", cart.toString()));
            throw new TransactionSystemException(String.format("Input data is inconsistent.\n%s", cart));
        } catch (InvalidDataAccessApiUsageException e) {
            log.error(String.format("Products should be saved first.\n%s", cart.toString()));
            throw new InvalidDataAccessApiUsageException(String.format("Products should be saved first.\n%s", cart));
        }
    }

    @Override
    @LogMehtod
    public List<Cart> getAmountForCustomer(String name) throws CartNotFoundException {
        log.info(String.format("Finding cart for client %s", name));
        return Optional.ofNullable(cartDAO.getCartsByClientName(name)).orElseThrow(() -> new CartNotFoundException("Card not found"));
    }

    @Override
    @LogMehtod
    public Product getPopularTopping() throws ToppingNotFoundException {
        log.info("Finding popular topping");
        /*
        TODO This is better to be done in the database or DAO (e.g. use findBy of JpaRepository). With the big databases could be problematic.
        Because I guess the dao.findAll() is executed regardless of what we have in filter
         */
        Map<String, List<OrderItem>> grouped = StreamSupport.stream(orderItmDAO.findAll().spliterator(), false).
                filter(i -> i.getProduct().getProductType() == TOPPING.ordinal()).
                collect(Collectors.groupingBy(i -> i.getProduct().getName()));
        //TODO I guess it is also could be done with lambda expressions
        int max = Integer.MIN_VALUE;
        Product product = null;
        for (Map.Entry<String, List<OrderItem>> item : grouped.entrySet()) {
            int size = item.getValue().size();
            if (max < size) {
                max = size;
                product = item.getValue().get(0).getProduct();
            }
        }
        return Optional.ofNullable(product).orElseThrow(() -> new ToppingNotFoundException("Topping Not found"));
    }

    @Override
    public void deleteAll() {
        cartDAO.deleteAll();
    }

    private BigDecimal calculateAmount(Cart cart) {
        return getBigDecimal(cart.getItems().
                stream().
                map(orderItem -> orderItem.getProduct().getPrice().doubleValue()).
                mapToDouble(Double::doubleValue).sum());
    }

    private Long calculateCount(Cart cart, ProductType productType) {
        return getStatistic(cart, productType).getCount();
    }

    private BigDecimal calculateMinItemAmount(Cart cart, ProductType productType) {
        return getBigDecimal(getStatistic(cart, productType).getMin());
    }

    private Cart calculateTicket(Cart cart) {
        Long count = calculateCount(cart, DRINK);
        BigDecimal amount = calculateAmount(cart);
        cart.setAmount(amount);
        if (count >= discountBasedOnCountOfDrinks) {
            BigDecimal minDrink = calculateMinItemAmount(cart, DRINK);
            BigDecimal minTopping = calculateMinItemAmount(cart, TOPPING);
            cart.setDiscount(minDrink.add(minTopping));
        } else {
            if (amount.doubleValue() > discountBasedOnPricePercent) {
                cart.setDiscount(amount.multiply(getBigDecimal(25)).divide(getBigDecimal(100)));
            } else {
                cart.setDiscount(getBigDecimal(0));
            }
        }
        cart.setTotal(cart.getAmount().subtract(cart.getDiscount()));
        return cart;
    }

    private BigDecimal getBigDecimal(double d) {
        return BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_DOWN);
    }

    private DoubleSummaryStatistics getStatistic(Cart cart, ProductType productType) {
        return cart.getItems().
                stream().
                filter(i -> i.getProduct().getProductType() == productType.ordinal()).
                collect(Collectors.summarizingDouble(i -> i.getProduct().getPrice().doubleValue()));
    }
}
