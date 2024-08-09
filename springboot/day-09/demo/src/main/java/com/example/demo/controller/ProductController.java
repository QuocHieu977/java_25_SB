package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    List<Product> products = new ArrayList<>(List.of(
            new Product(1, "iphone 15", "sản phẩm mới", 500, "apple"),
            new Product(2, "iphone 15 prm", "Sản phẩm mới", 600, "apple"),
            new Product(3, "iphone 14 prm", "Sản phẩm mới", 700, "apple"),
            new Product(4, "iphone 14 plus", "Sản phẩm mới", 800, "apple"),
            new Product(5, "iphone 12 pro", "Sản phẩm mới", 320, "apple"),
            new Product(6, "iphone 12", "Sản phẩm cũ", 300, "apple"),
            new Product(7, "iphone 12 prm", "Sản phẩm mới", 350, "apple"),
            new Product(8, "iphone 8 plus", "Sản phẩm cũ", 300, "apple"),
            new Product(9, "iphone 11", "Sản phẩm cũ", 300, "apple")
    ));

    @GetMapping("/products")
    public List<Product> list(){
        return products;
    }
}
