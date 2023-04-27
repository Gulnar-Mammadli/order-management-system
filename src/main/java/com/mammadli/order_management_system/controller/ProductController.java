package com.mammadli.order_management_system.controller;

import com.mammadli.order_management_system.dto.ProductDto;
import com.mammadli.order_management_system.model.Product;
import com.mammadli.order_management_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    @PostMapping
    Product createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }
}
