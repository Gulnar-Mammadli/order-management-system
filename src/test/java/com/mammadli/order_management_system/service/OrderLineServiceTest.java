package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.OrderLineDto;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.model.OrderLine;
import com.mammadli.order_management_system.model.Product;
import com.mammadli.order_management_system.repository.OrderLineRepository;
import com.mammadli.order_management_system.repository.OrderRepository;
import com.mammadli.order_management_system.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderLineServiceTest {

    @Mock
    private OrderLineRepository orderLineRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderLineService orderLineService;

    private static final Long ORDER_ID = 1L;
    private static final Long PRODUCT_ID = 1L;

    Order order = Order.builder()
            .id(ORDER_ID)
            .build();

    Product product =Product.builder()
            .id(PRODUCT_ID)
            .build();

    OrderLineDto orderLineDto = OrderLineDto.builder()
            .orderId(ORDER_ID)
            .productId(PRODUCT_ID)
            .quantity(5)
            .build();

    OrderLine orderLine = OrderLine.builder()
            .id(1L)
            .order(order)
            .product(product)
            .quantity(5)
            .build();

    @Test
    void createOrderLineSuccess() {
//        arrange
        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(orderLineRepository.save(any())).thenReturn(orderLine);

//        act
        OrderLine result = orderLineService.createOrderLine(orderLineDto);

//        assert
        assertNotNull(result);
        assertEquals(orderLine,result);
        verify(orderRepository).findById(any());
        verify(productRepository).findById(any());
        verify(orderLineRepository).save(any());
    }

    @Test
    void createOrderLineNotFound() {
//        arrange
        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        when(productRepository.findById(any())).thenReturn(Optional.empty());

//        act
        OrderLine result = orderLineService.createOrderLine(orderLineDto);

//        assert
        assertNull(result);
        verify(orderRepository).findById(any());
        verify(productRepository).findById(any());
    }

    @Test
    void updateProductQuantitySuccess() {
        int newQuantity = 7;

//        arrange
        when(orderLineRepository.findById(any())).thenReturn(Optional.of(orderLine));

//        act
        OrderLine result = orderLineService.updateProductQuantity(newQuantity,orderLine.getId());

//        assert
        assertNotNull(result);
        assertEquals(orderLine,result);
        verify(orderLineRepository).findById(any());
    }

    @Test
    void updateProductQuantityNotFound() {
        int newQuantity = 7;

//        arrange
        when(orderLineRepository.findById(any())).thenReturn(Optional.empty());

//        act
        OrderLine result = orderLineService.updateProductQuantity(newQuantity,orderLine.getId());

//        assert
        assertNull(result);
        verify(orderLineRepository).findById(any());
    }
}