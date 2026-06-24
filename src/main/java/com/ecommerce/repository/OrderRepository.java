package com.ecommerce.repository;

import com.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
    @Query("""
       SELECT COALESCE(SUM(o.totalAmount), 0)
       FROM Order o
       """)
    Double getTotalRevenue();
}