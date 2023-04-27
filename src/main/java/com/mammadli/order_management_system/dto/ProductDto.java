package com.mammadli.order_management_system.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {

    private String skuCode;
    private String name;
    private BigDecimal unitPrice;
}
