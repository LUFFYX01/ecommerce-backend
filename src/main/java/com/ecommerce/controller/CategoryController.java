package com.ecommerce.controller;

import com.ecommerce.entity.Category;
import com.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Categories",
        description = "Category Management APIs"
)
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
            summary = "Create Category",
            description = "Creates a new product category"
    )
    @PostMapping
    public Category createCategory(
            @RequestBody Category category) {

        return categoryService.createCategory(category);
    }

    @Operation(
            summary = "Get All Categories",
            description = "Retrieves all available categories"
    )
    @GetMapping
    public List<Category> getAllCategories() {

        return categoryService.getAllCategories();
    }
}