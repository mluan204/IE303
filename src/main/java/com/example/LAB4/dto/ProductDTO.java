package com.example.LAB4.dto;

import com.example.LAB4.entity.Product;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String brand;
    private String image;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.image = product.getImage();
    }

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(product);
    }
}
