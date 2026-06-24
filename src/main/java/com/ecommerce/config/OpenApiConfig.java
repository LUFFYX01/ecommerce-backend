package com.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ecommerceAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("E-Commerce Backend API")
                                .version("1.0")
                                .description(
                                        "Spring Boot E-Commerce Backend with JWT Authentication"
                                )
                );
    }
}