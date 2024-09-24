package com.example.shop.dto;

import com.example.shop.models.ProductModel;

public record ProductResponseDTO(String id, String name, Integer price) {
    public ProductResponseDTO (ProductModel product) {
        this(product.getId(), product.getName(), product.getPrice());
    }
}
