package com.mammadli.order_management_system.mapper;

import com.mammadli.order_management_system.dto.OrderLineDto;
import com.mammadli.order_management_system.model.OrderLine;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

    @Mapping( target = "order.id",source = "orderLineDto.orderId")
    @Mapping( target = "product.id",source = "orderLineDto.productId")
    OrderLine mapToOrderLine(OrderLineDto orderLineDto);
}
