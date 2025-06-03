package com.example.LAB4.service;

import com.example.LAB4.dto.ProductDTO;
import com.example.LAB4.entity.Product;
import com.example.LAB4.repository.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDTO> getAllProduct() {
        return repository
                .findAll(Sort.by(Sort.Direction.ASC, "id"))
                .stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }


}
