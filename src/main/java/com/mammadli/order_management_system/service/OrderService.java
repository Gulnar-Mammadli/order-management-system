package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.mapper.OrderMapper;
import com.mammadli.order_management_system.model.Customer;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.repository.CustomerRepository;
import com.mammadli.order_management_system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;


    public Order createOrder(OrderDto orderDto) {
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomerId());
        if (customer.isPresent()) {
            Order order = OrderMapper.INSTANCE.mapToOrder(orderDto);
            order.setCustomer(customer.get());
            return orderRepository.save(order);
        }
        return null;
    }

    public List<Order> findOrdersByDate(LocalDate date) {
        Optional<List<Order>> list = orderRepository.findAllByDate(date);
        return list.orElse(null);
    }
}
