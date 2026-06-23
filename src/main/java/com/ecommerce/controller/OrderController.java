package com.ecommerce.controller;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/place/{userId}")
    public Order placeOrder(
            @PathVariable Long userId) {

        return orderService.placeOrder(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(
            @PathVariable Long userId) {

        return orderService.getOrdersByUser(userId);
    }
    @GetMapping("/{orderId}")
    public List<OrderItem> getOrderDetails(
            @PathVariable Long orderId) {

        return orderService
                .getOrderDetails(orderId);
    }
}