package com.mammadli.order_management_system.repository;

import com.mammadli.order_management_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Optional<List<Order>> findAllByDate(LocalDate date);

    @Query("SELECT o FROM Order o JOIN o.orderLines ol WHERE ol.product.skuCode = :skuCode OR ol.product.name = :name")
    Optional<List<Order>> findOrdersByProduct(@Param("skuCode") String skuCode,
                                              @Param("name") String name);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId OR o.customer.registrationCode = :registrationCode " +
            "OR o.customer.fullName = :fullName OR o.customer.email = :email")
    Optional<List<Order>> findByCustomer(@Param("customerId") Long customerId,
                                         @Param("registrationCode") String registrationCode,
                                         @Param("fullName") String fullName,
                                         @Param("email") String email);

}
