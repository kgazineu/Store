package com.example.shop.repositories;

import com.example.shop.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {
    Optional<ProductModel> findById(String id);
}
