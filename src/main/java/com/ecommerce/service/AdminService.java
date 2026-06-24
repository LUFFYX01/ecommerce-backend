package com.ecommerce.service;

import com.ecommerce.dto.AdminStatsResponse;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public AdminStatsResponse getStats() {

        Long totalUsers =
                userRepository.count();

        Long totalProducts =
                productRepository.count();

        Long totalOrders =
                orderRepository.count();

        Double totalRevenue =
                orderRepository.getTotalRevenue();

        return new AdminStatsResponse(
                totalUsers,
                totalProducts,
                totalOrders,
                totalRevenue
        );
    }
}