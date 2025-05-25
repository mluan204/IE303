package com.example;

public class Product {
    private String name;
    private String description;
    private String category;
    private double price;
    private String imagePath;

    public Product(String name, String description, String category, double price, String imagePath) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }
}