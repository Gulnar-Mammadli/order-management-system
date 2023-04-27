package com.mammadli.order_management_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {

    private String registrationCode;
    private String fullName;
    private String email;
    private String telephone;

}
