package com.ecommerce.controller;

import com.ecommerce.dto.RegisterRequest;
import com.ecommerce.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.AuthResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request) {

        return authService.login(request);
    }
}