package com.ecommerce.service;

import com.ecommerce.entity.CartItem;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderItem;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;
import com.ecommerce.repository.OrderItemRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;
import com.ecommerce.exception.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User Not Found"));

        List<CartItem> cartItems =
                cartRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {

            throw new CartEmptyException(
                    "Cart is Empty"
            );
        }

        Order order = new Order();

        order.setUser(user);
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0;

        order = orderRepository.save(order);

        for (CartItem cartItem : cartItems) {

            Product product = cartItem.getProduct();

            if (product.getStock() < cartItem.getQuantity()) {

                throw new InsufficientStockException(
                        "Insufficient Stock for "
                                + product.getName()
                );
            }

            product.setStock(
                    product.getStock()
                            - cartItem.getQuantity()
            );

            productRepository.save(product);

            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());

            orderItem.setQuantity(
                    cartItem.getQuantity()
            );

            orderItem.setPrice(
                    cartItem.getProduct().getPrice()
            );

            totalAmount +=
                    cartItem.getProduct().getPrice()
                            * cartItem.getQuantity();

            orderItemRepository.save(orderItem);
        }



        order.setTotalAmount(totalAmount);

        orderRepository.save(order);

        cartRepository.deleteAll(cartItems);

        return order;
    }

    public List<OrderItem> getOrderDetails(
            Long orderId) {

        return orderItemRepository
                .findByOrderId(orderId);
    }

    public Order placeOrder() {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        return placeOrder(user.getId());
    }
    public List<Order> getMyOrders() {

        String email =
                SecurityUtils.getCurrentUserEmail();

        User user =
                userRepository.findByEmail(email)
                        .orElseThrow();

        return orderRepository.findByUserId(
                user.getId()
        );
    }
}