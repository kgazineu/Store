package com.example.shop.controller;

import com.example.shop.dto.ProductRecordDTO;
import com.example.shop.models.ProductModel;
import com.example.shop.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
public class ProductController {

    //    Injetando o repository no meu controller
    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")              // O corpo da requisição vai seguir o que foi predefinido no DTO (como está sendo chamado no saveProduct)
                                            // O @Valid vai validar o corpo da requisição se segue os padrões das anotações do DTO
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO) {
        var productModel = new ProductModel();
        copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productList = productRepository.findAll();
        if(!productList.isEmpty()) {
            for(ProductModel product : productList) {
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withRel("List of Products"));
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDTO productRecordDTO) {
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product.get();
        copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

}
