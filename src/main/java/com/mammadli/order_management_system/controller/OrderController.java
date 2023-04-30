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
    Order createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/date/{date}")
    List<Order> getOrdersByDate(@PathVariable LocalDate date) {
        return orderService.findOrdersByDate(date);
    }

    @GetMapping("/product-jpql")
    List<Order> fetchOrdersByProduct(@RequestParam(required = false) String skuCode,
                                     @RequestParam(required = false) String name) {
        return orderService.fetchOrdersByProduct(skuCode, name);
    }

    @GetMapping("/product-criteria")
    List<Order> getOrdersByProduct(
            @RequestParam(name = "skuCode", required = false) String skuCode,
            @RequestParam(name = "name", required = false) String name) {
        return orderService.findOrdersByProduct(skuCode, name);
    }

    @GetMapping("/customer-jpql")
    List<Order> fetchOrdersByCustomer(
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "registrationCode", required = false) String registrationCode,
            @RequestParam(name = "fullName", required = false) String fullName,
            @RequestParam(name = "email", required = false) String email
    ) {
        return orderService.fetchOrdersByCustomer(customerId,registrationCode,fullName,email);
    }

    @GetMapping("/customer-criteria")
    public List<Order> getOrdersByCustomer(
            @RequestParam(name = "customerId", required = false) Long customerId,
            @RequestParam(name = "registrationCode", required = false) String registrationCode,
            @RequestParam(name = "fullName", required = false) String fullName,
            @RequestParam(name = "email", required = false) String email
    ) {
        return orderService.findOrdersByCustomer(customerId, registrationCode, fullName, email);
    }
}
