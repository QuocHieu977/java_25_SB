package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductController {
    List<Product> products = new ArrayList<>(List.of(
            new Product(1, "iphone 15", "sản phẩm mới", 500, "apple"),
            new Product(2, "iphone 15 prm", "Sản phẩm mới", 600, "apple"),
            new Product(3, "iphone 14 prm", "Sản phẩm mới", 700, "apple"),
            new Product(4, "iphone 14 plus", "Sản phẩm mới", 800, "apple"),
            new Product(5, "iphone 12 pro", "Sản phẩm mới", 320, "apple"),
            new Product(6, "iphone 12", "Sản phẩm cũ", 300, "apple"),
            new Product(7, "iphone 12 prm", "Sản phẩm mới", 350, "apple"),
            new Product(8, "samsung s24", "Sản phẩm cũ", 500, "samsung"),
            new Product(9, "samsung s21", "Sản phẩm cũ", 300, "samsung")
    ));

    @GetMapping("/products")
    public ResponseEntity<List<Product>> list(){
        return ResponseEntity.ok(products);
    }

//    1. Lấy thông tin chi tiết của một sản phẩm
//    API: GET /products/{id}
//    Mô tả: Trả về chi tiết của sản phẩm dựa trên id được cung cấp.
    @GetMapping("products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        for(Product product: products) {
            if(product.getId() == id) {
                return ResponseEntity.ok(product);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    2. Lấy sản phẩm với tên bắt đầu bằng prefix nào đó
//    API: GET /products/name-starts/{prefix}
//    Mô tả: Trả về danh sách sản phẩm có tên bắt đầu bằng nào đó.
    @GetMapping("products/name-starts/{prefix}")
    public ResponseEntity<List<Product>> getProductByPrefix(@PathVariable String prefix) {
        List<Product> results = new ArrayList<>();
        for(Product product : products) {
            if(product.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                results.add(product);
            }
        }
        if(products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

//    3. Lọc sản phẩm theo khoảng giá
//    API: GET /products/price/{min}/{max}
//    Mô tả: Trả về danh sách sản phẩm có giá nằm trong khoảng từ min đến max.
    @GetMapping("products/price/{min}/{max}")
    public ResponseEntity<List<Product>> getProductByPriceRange(@PathVariable int min, @PathVariable int max) {
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                results.add(product);
            }
        }
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(results);
    }
//    4. Lấy sản phẩm theo thương hiệu
//    API: GET /products/brand/{brand}
//    Mô tả: Trả về danh sách sản phẩm thuộc thương hiệu được chỉ định.

    @GetMapping("products/brand/{brand}")
    public ResponseEntity<List<Product>> getProductByBrand(@PathVariable String brand) {
        List<Product> results = new ArrayList<>();
        for(Product product: products) {
            if(product.getBrand().equals(brand))
                results.add(product);
        }
        if (results.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

//    5. Lấy sản phẩm có giá cao nhất
//    API: GET /products/brand/{brand}/max-price
//    Mô tả: Trả về sản phẩm có giá cao nhất theo brand được chỉ định.
    @GetMapping("products/brand/{brand}/max-price")
    public ResponseEntity<Product> getProductByBrandMaxPrice(@PathVariable String brand) {
        List<Product> results = new ArrayList<>();
        for(Product product: products) {
            if(product.getBrand().equals(brand))
                results.add(product);
        }

        results.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o2.getPrice() - o1.getPrice());
            }
        });
        return ResponseEntity.ok(results.get(0));
    }
}
