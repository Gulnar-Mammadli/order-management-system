package com.mammadli.order_management_system.mapper;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping( target = "customer.id",source = "orderDto.customerId")
    Order mapToOrder(OrderDto orderDto);
}
