package com.mammadli.order_management_system.mapper;

import com.mammadli.order_management_system.dto.CustomerDto;
import com.mammadli.order_management_system.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer mapToCustomer(CustomerDto customerDto);
}
