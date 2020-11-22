package com.microservice.assignment.starbux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true, defaultValue = "drink")
    private String productType;

    private BigDecimal price;
}
