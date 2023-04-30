package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.mapper.OrderMapper;
import com.mammadli.order_management_system.model.Customer;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.model.OrderLine;
import com.mammadli.order_management_system.model.Product;
import com.mammadli.order_management_system.repository.CustomerRepository;
import com.mammadli.order_management_system.repository.OrderRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
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

    public List<Order> fetchOrdersByProduct(String skuCode, String name) {
        Optional<List<Order>> result = orderRepository.findOrdersByProduct(skuCode, name);
        return result.orElse(null);
    }

    public List<Order> findOrdersByProduct(String skuCode, String name) {

        return orderRepository.findAll((root, query, criteriaBuilder) -> {
                    Join<Order, OrderLine> orderLineJoin = root.join("orderLines");
                    Join<OrderLine, Product> productJoin = orderLineJoin.join("product");
                    Predicate predicateSkuCode = criteriaBuilder.equal(productJoin.get("skuCode"), skuCode);
                    Predicate predicateName = criteriaBuilder.equal(productJoin.get("name"), name);
                    return criteriaBuilder.or(predicateSkuCode, predicateName);
                }
        );
    }

    public List<Order> findOrdersByCustomer(Long customerId, String registrationCode, String fullName, String email) {

        return orderRepository.findAll((root, query, criteriaBuilder) -> {
                    Predicate predicateCustomerId = criteriaBuilder.equal(root.get("customer").get("id"), customerId);
                    Predicate predicateRegistrationCode = criteriaBuilder.equal(root.get("customer").get("registrationCode"), registrationCode);
                    Predicate predicateFullName = criteriaBuilder.equal(root.get("customer").get("fullName"), fullName);
                    Predicate predicateEmail = criteriaBuilder.equal(root.get("customer").get("email"), email);
                    return criteriaBuilder.or(predicateCustomerId, predicateRegistrationCode, predicateFullName, predicateEmail);
                }
        );
    }


    public List<Order> fetchOrdersByCustomer(Long customerId, String registrationCode, String fullName, String email) {
        Optional<List<Order>> result = orderRepository.findByCustomer(customerId, registrationCode, fullName, email);
        return result.orElse(null);
    }


}
