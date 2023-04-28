package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.CustomerDto;
import com.mammadli.order_management_system.model.Customer;
import com.mammadli.order_management_system.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private static final  String REGISTRATION_CODE = "123";
    private static final  String FULL_NAME = "Jon";
    private static final  String EMAIL = "jon12@gmail.com";
    private static final  String TELEPHONE = "+37267783123";


    CustomerDto customerDto = CustomerDto.builder()
            .registrationCode(REGISTRATION_CODE)
            .fullName(FULL_NAME)
            .email(EMAIL)
            .telephone(TELEPHONE)
            .build();

    Customer customer = Customer.builder()
            .id(1L)
            .registrationCode(REGISTRATION_CODE)
            .fullName(FULL_NAME)
            .email(EMAIL)
            .telephone(TELEPHONE)
            .build();


    @Test
    void createCustomerSuccess() {

        //Arrange
        when(customerRepository.findByRegistrationCode(any())).thenReturn(Optional.empty());
        when(customerRepository.save(any())).thenReturn(customer);

        //Act
        Customer result = customerService.createCustomer(customerDto);

        //Assert
        assertNotNull(result);
        assertEquals(customer,result);
        verify(customerRepository).findByRegistrationCode(any());
        verify(customerRepository).save(any());

    }

    @Test
    void createCustomerWhenCustomerExists(){

        //Arrange
        when(customerRepository.findByRegistrationCode(any())).thenReturn(Optional.of(customer));

        //Act
        Customer result = customerService.createCustomer(customerDto);

        //Assert
        assertNull(result);
        verify(customerRepository).findByRegistrationCode(any());

    }
}