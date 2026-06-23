package com.ecommerce.service;

import com.ecommerce.dto.CartRequest;
import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartItem addToCart(
            CartRequest request) {

        User user = userRepository.findById(
                request.getUserId()
        ).orElseThrow();

        Product product = productRepository.findById(
                request.getProductId()
        ).orElseThrow();

        CartItem cartItem = new CartItem();

        cartItem.setUser(user);
        cartItem.setProduct(product);
        cartItem.setQuantity(
                request.getQuantity()
        );

        return cartRepository.save(cartItem);
    }

    public List<CartItem> getCart(
            Long userId) {

        return cartRepository.findByUserId(
                userId
        );
    }

    public String removeFromCart(Long cartItemId) {

        cartRepository.deleteById(cartItemId);

        return "Item Removed From Cart";
    }
}