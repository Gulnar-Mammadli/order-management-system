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
import java.util.*;

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


    private static final String PRODUCT_NAME = "Phone";
    private static final String PRODUCT_SKU_CODE = "123";


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
    public void testFetchOrdersByProductSuccess() {
//        arrange
        when(orderRepository.findOrdersByProduct(any(),any())).thenReturn(Optional.of(orderList));

//        act
        List<Order> result = orderService.fetchOrdersByProduct(PRODUCT_SKU_CODE, PRODUCT_NAME);

//        assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findOrdersByProduct(any(),any());
    }


    @Test
    public void testFetchOrdersNotFound() {
//        arrange
        when(orderRepository.findOrdersByProduct(any(),any())).thenReturn(Optional.empty());

//        act
        List<Order> result = orderService.fetchOrdersByProduct(PRODUCT_SKU_CODE, PRODUCT_NAME);

//        assert
        assertNull(result);
        verify(orderRepository).findOrdersByProduct(any(),any());
    }




    @Test
    public void findOrdersByProductSuccess() {
//        arrange
        when(orderRepository.findAll(any(Specification.class))).thenReturn(orderList);

        //        act
        List<Order> result = orderService.findOrdersByProduct(PRODUCT_SKU_CODE, PRODUCT_NAME);
        assertEquals(orderList, result);

//        assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findAll(any(Specification.class));
    }


    @Test
    public void testFindOrdersByCustomerSuccess() {
//      arrange
        when(orderRepository.findAll(any(Specification.class))).thenReturn(orderList);

//        act
        List<Order> result = orderService.findOrdersByCustomer(customer.getId(), customer.getRegistrationCode(), customer.getFullName(), customer.getEmail());

//        assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findAll(any(Specification.class));

    }

    @Test
    public void testFindOrdersByCustomerNotFound() {
//      arrange
        when(orderRepository.findAll(any(Specification.class))).thenReturn(null);

//        act
        List<Order> result = orderService.findOrdersByCustomer(customer.getId(), customer.getRegistrationCode(), customer.getFullName(), customer.getEmail());

//        assert
        assertNull(result);
        verify(orderRepository).findAll(any(Specification.class));

    }

    @Test
    public void testFetchOrdersByCustomerSuccess() {
//        arrange
        when(orderRepository.findByCustomer(any(),any(),any(),any())).thenReturn(Optional.of(orderList));

//        act
        List<Order> result = orderService.fetchOrdersByCustomer(customer.getId(), customer.getRegistrationCode(), customer.getFullName(), customer.getEmail());

//        assert
        assertNotNull(result);
        assertEquals(orderList, result);
        verify(orderRepository).findByCustomer(any(),any(),any(),any());
    }

    @Test
    public void testFetchOrdersByCustomerNotFound() {
//        arrange
        when(orderRepository.findByCustomer(any(),any(),any(),any())).thenReturn(Optional.empty());

//        act
        List<Order> result = orderService.fetchOrdersByCustomer(customer.getId(), customer.getRegistrationCode(), customer.getFullName(), customer.getEmail());

//        assert
        assertNull(result);
        verify(orderRepository).findByCustomer(any(),any(),any(),any());
    }

}