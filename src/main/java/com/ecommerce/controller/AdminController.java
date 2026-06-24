package com.ecommerce.controller;

import com.ecommerce.dto.AdminStatsResponse;
import com.ecommerce.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Admin Dashboard",
        description = "Administrative Statistics and Monitoring APIs"
)
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @Operation(
            summary = "Get Dashboard Statistics",
            description = "Returns total users, products, orders and revenue for the admin dashboard"
    )
    @GetMapping("/stats")
    public AdminStatsResponse getStats() {

        return adminService.getStats();
    }
}