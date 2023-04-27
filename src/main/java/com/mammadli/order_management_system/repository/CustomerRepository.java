package com.mammadli.order_management_system.repository;

import com.mammadli.order_management_system.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByRegistrationCode( String code);
}
