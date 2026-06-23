package com.ecommerce.controller;

import com.ecommerce.dto.CartRequest;
import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(
            @RequestBody CartRequest request) {

        return cartService.addToCart(request);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(
            @PathVariable Long userId) {

        return cartService.getCart(userId);
    }
    @DeleteMapping("/{cartItemId}")
    public String removeFromCart(
            @PathVariable Long cartItemId) {

        return cartService.removeFromCart(
                cartItemId
        );
    }
}