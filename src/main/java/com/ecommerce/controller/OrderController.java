package com.ecommerce.controller;

import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.security.SecurityUtils;

import java.util.List;

@Tag(
        name = "Orders",
        description = "Order Management APIs"
)
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Place Order",
            description = "Creates an order from all items currently present in the user's cart"
    )
    @PostMapping("/place")
    public Order placeOrder() {

        return orderService.placeOrder();
    }

    @Operation(
            summary = "Get Order History",
            description = "Retrieves all orders placed by a specific user"
    )

    @GetMapping("/{orderId}")
    public List<OrderItem> getOrderDetails(
            @PathVariable Long orderId) {

        return orderService
                .getOrderDetails(orderId);
    }
    @Operation(
            summary = "Get Order Details",
            description = "Retrieves all products, quantities and prices associated with an order"
    )
    @GetMapping("/my-orders")
    public List<Order> getMyOrders() {

        return orderService.getMyOrders();
    }
}