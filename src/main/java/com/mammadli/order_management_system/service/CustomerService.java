package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.CustomerDto;
import com.mammadli.order_management_system.mapper.CustomerMapper;
import com.mammadli.order_management_system.model.Customer;
import com.mammadli.order_management_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findByRegistrationCode(customerDto.getRegistrationCode());
        if(customer.isEmpty()){
            return customerRepository.save(CustomerMapper.INSTANCE.mapToCustomer(customerDto));
        }
        return null;
    }
}
