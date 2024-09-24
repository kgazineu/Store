package com.example.shop.controller;

import com.example.shop.dto.ProductRequestDTO;
import com.example.shop.dto.ProductResponseDTO;
import com.example.shop.models.ProductModel;
import com.example.shop.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequestMapping("products")
public class ProductController {

    //    Injetando o repository no meu controller
    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody @Valid ProductRequestDTO body) {
        ProductModel newProduct = new ProductModel(body);
        this.productRepository.save(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<ProductResponseDTO> productList = this.productRepository
                .findAll().stream()
                .map(ProductResponseDTO::new).toList();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") String id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        product.get();
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") String id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") String id,
                                                @RequestBody @Valid ProductRequestDTO productRecordDTO) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product.get();
        copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

}
