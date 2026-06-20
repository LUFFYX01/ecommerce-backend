package com.ecommerce.repository;

import com.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository
        extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);
}