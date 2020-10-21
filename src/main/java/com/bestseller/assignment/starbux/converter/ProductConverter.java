package com.bestseller.assignment.starbux.converter;

import com.bestseller.assignment.starbux.domainentitiy.Product;
import com.bestseller.assignment.starbux.domainentitiy.ProductType;
import com.bestseller.assignment.starbux.dto.ProductDTO;

/**
 * This class converts different product objects from the dto to domain object and vice versa.
 */
public class ProductConverter {

    public ProductDTO toResource(Product product) {
        return ProductDTO.builder().name(product.getName()).
                price(product.getPrice()).
                productType(ProductType.getNameType(product.getProductType())).
                build();
    }

    public Product toModel(ProductDTO productDTO) {
        return new Product(productDTO.getName(), ProductType.getCode(productDTO.getProductType()), productDTO.getPrice());
    }
}
