package com.mammadli.order_management_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderLineDto {

    private Long orderId;
    private Long productId;
    private int quantity;
}
