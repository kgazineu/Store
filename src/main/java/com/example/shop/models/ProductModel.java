package com.example.shop.models;

import com.example.shop.dto.product.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "product") @Entity(name = "TB_PRODUCTS")
@Getter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductModel{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer price;

    public ProductModel(ProductRequestDTO data){
        this.price = data.price();
        this.name = data.name();
    }
}