package com.microservice.assignment.starbux.converter;

import com.microservice.assignment.starbux.domainentitiy.Product;
import com.microservice.assignment.starbux.domainentitiy.ProductType;
import com.microservice.assignment.starbux.dto.ProductDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductConverterTest {

    private ProductConverter productConverter = new ProductConverter();

    @DisplayName("Tests converting a (Product) from model to resource")
    @Test
    public void toResourceTest() {
        Product product = new Product("Americano", ProductType.DRINK.ordinal(), BigDecimal.valueOf(2l));
        ProductDTO productDTO = productConverter.toResource(product);
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(ProductType.getNameType(product.getProductType()), productDTO.getProductType());
        assertEquals(product.getPrice(), productDTO.getPrice());
    }

    @DisplayName("Tests converting a (ProductDTO) from resource to model")
    @Test
    public void toModelTest() {
        ProductDTO productDTO = ProductDTO.builder().name("Americano").productType("drink").price(BigDecimal.valueOf(2l)).build();
        Product product = productConverter.toModel(productDTO);
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(ProductType.getCode(productDTO.getProductType()), product.getProductType());
        assertEquals(productDTO.getPrice(), product.getPrice());
    }
}
