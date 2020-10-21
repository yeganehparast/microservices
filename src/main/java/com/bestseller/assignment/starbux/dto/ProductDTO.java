package com.bestseller.assignment.starbux.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * This is the Product dto class for the processes related to the controller class
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDTO {

    private String name;

    private String productType;

    private BigDecimal price;
}
