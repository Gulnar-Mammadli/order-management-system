package com.mammadli.order_management_system.controller;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    Order createOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/date/{date}")
    List<Order> getOrdersByDate(@PathVariable LocalDate date){
        return orderService.findOrdersByDate(date);
    }

    @GetMapping("/productId/{productId}")
    List<Order> getOrdersByProduct(@PathVariable Long productId){
        return orderService.findOrdersByProduct(productId);
    }
}
