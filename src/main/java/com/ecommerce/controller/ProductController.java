package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(
        name = "Products",
        description = "Product Management APIs"
)
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Create Product",
            description = "Creates a new product in the database"
    )
    @PostMapping
    public Product createProduct(
            @RequestBody Product product) {

        return productService.createProduct(product);
    }

    @Operation(
            summary = "Get All Products",
            description = "Retrieves all available products"
    )
    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @Operation(
            summary = "Get Product By ID",
            description = "Retrieves product information using product ID"
    )
    @GetMapping("/{id}")
    public Product getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    @Operation(
            summary = "Update Product",
            description = "Updates an existing product"
    )
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(
                id,
                product
        );
    }

    @Operation(
            summary = "Delete Product",
            description = "Deletes a product from the database"
    )
    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        return productService.deleteProduct(id);
    }

    @Operation(
            summary = "Admin Test Endpoint",
            description = "Used to verify ADMIN role access"
    )
    @GetMapping("/test")
    public String test() {
        return "ADMIN ACCESS WORKS";
    }
}