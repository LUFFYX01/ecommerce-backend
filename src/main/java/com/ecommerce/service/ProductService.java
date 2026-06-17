package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));
    }

    public Product updateProduct(
            Long id,
            Product updatedProduct) {

        Product product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product Not Found"));

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        product.setCategory(updatedProduct.getCategory());
        product.setImageUrl(updatedProduct.getImageUrl());

        return productRepository.save(product);
    }
    public String deleteProduct(Long id) {

        productRepository.deleteById(id);

        return "Product Deleted";
    }
}