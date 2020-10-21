package com.bestseller.assignment.starbux.dto;

import lombok.*;

/**
 * This is the OrderItem dto class for the processes related to the controller class
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItemDTO {

    private ProductDTO productDTO;

}
