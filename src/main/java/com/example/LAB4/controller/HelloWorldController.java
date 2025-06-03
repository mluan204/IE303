package com.example.LAB4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {
    @GetMapping
    public String helloWorld() {
        return "truy cập http://localhost:8080/api/products để xem api lấy danh sách sản phẩm";
    }
}
