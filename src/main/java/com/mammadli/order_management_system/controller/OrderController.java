package com.mammadli.order_management_system.controller;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    @GetMapping("/customerId/{customerId}")
    List<Order> findOrdersByCustomer(@PathVariable Long customerId){
        return orderService.findOrdersByCustomer(customerId);
    }

    @GetMapping("/orders")
    public List<Order> getOrdersByCustomer(
            @RequestParam(name = "customerId", required = false) Long customerId) {

        Specification<Order> spec = null;

        if (customerId != null) {
            spec = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("customer").get("id"), customerId));
        }

        return orderService.findOrdersByCustomer(spec);
    }
}
