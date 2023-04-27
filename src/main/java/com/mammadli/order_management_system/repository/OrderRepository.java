package com.mammadli.order_management_system.repository;

import com.mammadli.order_management_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByCustomerId(Long customerId);
    Optional<List<Order>> findAllByDate(LocalDate date);

    @Query("SELECT o FROM Order o JOIN o.orderLines ol WHERE ol.product.id = :productId")
    List<Order> findOrdersByProductId(@Param("productId") Long productId);
}
