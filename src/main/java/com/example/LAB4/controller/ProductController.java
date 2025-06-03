package com.example.LAB4.controller;

import com.example.LAB4.dto.ProductDTO;
import com.example.LAB4.repository.ProductRepository;
import com.example.LAB4.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> products() {
        return ResponseEntity.ok(productService.getAllProduct());
    }
}
