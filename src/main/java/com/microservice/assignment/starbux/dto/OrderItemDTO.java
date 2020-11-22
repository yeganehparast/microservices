package com.microservice.assignment.starbux.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(required = true)
    private ProductDTO productDTO;

}
