package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.ProductDto;
import com.mammadli.order_management_system.model.Product;
import com.mammadli.order_management_system.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private static final String SKU_CODE = "1234";
    private static final String NAME = "Phone";
    private static final BigDecimal UNIT_PRICE = BigDecimal.valueOf(1300L);

    Product product = Product.builder()
            .id(1L)
            .skuCode(SKU_CODE)
            .name(NAME)
            .unitPrice(UNIT_PRICE)
            .build();

    ProductDto productDto = ProductDto.builder()
            .skuCode(SKU_CODE)
            .name(NAME)
            .unitPrice(UNIT_PRICE)
            .build();

    @Test
    void createProductSuccess() {

        //arrange
        when(productRepository.findBySkuCode(any())).thenReturn(Optional.empty());
        when(productRepository.save(any())).thenReturn(product);

        //act
        Product result = productService.createProduct(productDto);

        //assert
        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository).findBySkuCode(any());
        verify(productRepository).save(any());
    }

    @Test
    void createProductWhenProductExists() {

        //arrange
        when(productRepository.findBySkuCode(any())).thenReturn(Optional.of(product));

        //act
        Product result = productService.createProduct(productDto);

        //assert
        assertNull(result);
        verify(productRepository).findBySkuCode(any());
    }
}