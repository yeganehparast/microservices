package com.bestseller.assignment.starbux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * This is the Cart dto class for the processes related to the controller class
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartDTO {
    @JsonProperty(required = true)
    private String clientName;

    private BigDecimal total;

    private BigDecimal discount;

    private BigDecimal amount;

    private List<OrderItemDTO> items;
}
