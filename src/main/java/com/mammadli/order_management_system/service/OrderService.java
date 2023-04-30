package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.mapper.OrderMapper;
import com.mammadli.order_management_system.model.Customer;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.repository.CustomerRepository;
import com.mammadli.order_management_system.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    public List<Order> findOrdersByProduct(Long productId) {

        Optional<List<Order>> orderList = orderRepository.findOrdersByProductId(productId);
        return orderList.orElse(null);
    }


    public List<Order> findOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }


    public List<Order> findOrdersByCustomer(Long customerId, String registrationCode, String fullName, String email) {
        Specification<Order> spec = null;
        if (customerId != null) {
            spec = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("customer").get("id"), customerId));
        }
        if (registrationCode != null) {
            spec = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("customer").get("registrationCode"), registrationCode));
        }

        if (fullName != null) {
            spec = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("customer").get("fullName"), fullName));
        }
        if (email != null) {
            spec = Specification.where((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("customer").get("email"), email));
        }
        return orderRepository.findAll(spec);
    }


}
