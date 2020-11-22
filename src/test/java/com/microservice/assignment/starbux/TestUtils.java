package com.microservice.assignment.starbux;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.assignment.starbux.converter.ProductConverter;
import com.microservice.assignment.starbux.domainentitiy.Product;
import com.microservice.assignment.starbux.domainentitiy.ProductType;
import com.microservice.assignment.starbux.dto.CartDTO;
import com.microservice.assignment.starbux.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.*;

import static java.math.RoundingMode.HALF_DOWN;

/**
 * Contains the utility methods for the tests
 */
public class TestUtils {

    public static List<String> drinks = Arrays.asList("cappuccino", "espresso", "latte", "macchiato", "tea");
    public static List<String> toppings = Arrays.asList("caramel", "cinnamon", "honey", "lemon", "cream");

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Product> getProductMap() {
        Map<String, Product> productMap = getProductMap(drinks, ProductType.DRINK);
        productMap.putAll(getProductMap(toppings, ProductType.TOPPING));
        return productMap;
    }

    public static BigDecimal getPriceFromDouble(double value) {
        return BigDecimal.valueOf(value).setScale(2, HALF_DOWN);
    }

    public static BigDecimal getPriceFromDouble(double value, int scale) {
        return BigDecimal.valueOf(value).setScale(scale, HALF_DOWN);
    }

    private static Map<String, Product> getProductMap(List<String> strings, ProductType productType) {
        Map<String, Product> productMap = new HashMap<>();
        strings.forEach(d -> {
            Product product = new Product(d, productType.ordinal(), BigDecimal.valueOf(getRandomPrice(1, 5)).setScale(2, HALF_DOWN));
            productMap.put(d, product);
        });
        return productMap;
    }

    public static double getRandomPrice(int min, int max) {
        return (Math.random() * (max - min) + 1) + min;
    }

    public static List<String> getProductDTOJsonList() {
        Map<String, Product> productMap = getProductMap();
        ProductConverter productConverter = new ProductConverter();
        ArrayList<String> result = new ArrayList<>();
        productMap.values().stream().forEach(product -> {
            try {
                result.add(objectMapper.writeValueAsString(productConverter.toResource(product)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    public static ProductDTO getProductDTO(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, ProductDTO.class);
    }

    public static CartDTO getCart(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, CartDTO.class);
    }

    public static String getCartDTOJson(CartDTO cartDTO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(cartDTO);
    }
}
