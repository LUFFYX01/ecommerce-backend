package com.ecommerce.controller;

import com.ecommerce.dto.CartRequest;
import com.ecommerce.entity.CartItem;
import com.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Cart",
        description = "Shopping Cart APIs"
)
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @Operation(
            summary = "Add Product To Cart",
            description = "Adds a selected product to the user's shopping cart"
    )
    @PostMapping("/add")
    public CartItem addToCart(
            @RequestBody CartRequest request) {

        return cartService.addToCart(request);
    }

    @Operation(
            summary = "View Cart",
            description = "Retrieves all items present in a user's shopping cart"
    )
    @GetMapping("/{userId}")
    public List<CartItem> getCart(
            @PathVariable Long userId) {

        return cartService.getCart(userId);
    }

    @Operation(
            summary = "Remove Cart Item",
            description = "Removes a specific item from the shopping cart"
    )
    @DeleteMapping("/{cartItemId}")
    public String removeFromCart(
            @PathVariable Long cartItemId) {

        return cartService.removeFromCart(
                cartItemId
        );
    }
}