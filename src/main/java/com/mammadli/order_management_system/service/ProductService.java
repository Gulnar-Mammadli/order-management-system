package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.ProductDto;
import com.mammadli.order_management_system.mapper.ProductMapper;
import com.mammadli.order_management_system.model.Product;
import com.mammadli.order_management_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public Product createProduct(ProductDto productDto) {
        Optional<Product> product = productRepository.findBySkuCode(productDto.getSkuCode());
        if (product.isEmpty()) {
            return productRepository.save(ProductMapper.INSTANCE.mapToProduct(productDto));
        }
        return null;
    }
}
