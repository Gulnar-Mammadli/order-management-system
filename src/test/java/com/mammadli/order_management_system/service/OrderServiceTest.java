package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.OrderDto;
import com.mammadli.order_management_system.model.Customer;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.repository.CustomerRepository;
import com.mammadli.order_management_system.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;


    private static final Long PRODUCT_ID = 1L;

    Customer customer = Customer.builder()
            .id(1L)
            .registrationCode("123")
            .fullName("Jane")
            .email("jane12@gmail.com")
            .telephone("+37234567821")
            .build();

    Order order = Order.builder()
            .id(1L)
            .date(LocalDate.now())
            .customer(customer)
            .build();

    Order order2 = Order.builder()
            .id(2L)
            .date(LocalDate.now())
            .customer(customer)
            .build();
    OrderDto orderDto = OrderDto.builder()
            .customerId(customer.getId())
            .date(LocalDate.now())
            .build();

    List<Order> orderList = Arrays.asList(order, order2);

    @Test
    void createOrderSuccess() {
//        arrange
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        when(orderRepository.save(any())).thenReturn(order);

//        act
        Order result = orderService.createOrder(orderDto);

//        assert
        assertNotNull(result);
        assertEquals(order, result);
        verify(customerRepository).findById(any());
        verify(orderRepository).save(any());

    }

    @Test
    void createOrderWhenCustomerNotFound() {

//        arrange
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

//        act
        Order result = orderService.createOrder(orderDto);

//        assert
        assertNull(result);
        verify(customerRepository).findById(any());

    }

    @Test
    void findOrdersByDateSuccess() {

//        arrange
        when(orderRepository.findAllByDate(any())).thenReturn(Optional.of(orderList));

//        act
        List<Order> result = orderService.findOrdersByDate(LocalDate.now());

//        assert
        assertEquals(orderList, result);
        verify(orderRepository).findAllByDate(any());

    }


    @Test
    void findOrdersByDateNotFound() {

//        arrange
        when(orderRepository.findAllByDate(any())).thenReturn(Optional.empty());

//        act
        List<Order> result = orderService.findOrdersByDate(LocalDate.now());

//        assert
        assertNull(result);
        verify(orderRepository).findAllByDate(any());

    }

    @Test
    void findOrdersByProductSuccess() {

//        arrange
        when(orderRepository.findOrdersByProductId(any())).thenReturn(Optional.of(orderList));

//        act
        List<Order> result = orderService.findOrdersByProduct(PRODUCT_ID);

//        assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findOrdersByProductId(any());
    }

    @Test
    void findOrdersByProductNotFound() {

//        arrange
        when(orderRepository.findOrdersByProductId(any())).thenReturn(Optional.empty());

//        act
        List<Order> result = orderService.findOrdersByProduct(PRODUCT_ID);

//        assert
        assertNull(result);
        verify(orderRepository).findOrdersByProductId(any());
    }

    @Test
    void findOrdersByCustomerSuccess() {
        //        arrange
        when(orderRepository.findByCustomerId(any())).thenReturn(orderList);

//        act
        List<Order> result = orderService.findOrdersByCustomer(customer.getId());

//        assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findByCustomerId(any());
    }

    @Test
    void testFindOrdersByCustomer() {

        // Arrange
        Specification<Order> spec = mock(Specification.class);
        when(orderRepository.findAll(spec)).thenReturn(orderList);


        // Act
        List<Order> result = orderService.findOrdersByCustomer(spec);

        // Assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findAll(spec);

    }
}