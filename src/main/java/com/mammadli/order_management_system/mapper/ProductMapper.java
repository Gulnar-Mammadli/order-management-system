package com.mammadli.order_management_system.mapper;

import com.mammadli.order_management_system.dto.ProductDto;
import com.mammadli.order_management_system.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

   ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product mapToProduct(ProductDto productDto);
}
