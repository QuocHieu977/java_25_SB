package org.example.projectintroproduct.controller;

import org.example.projectintroproduct.model.PageResponse;
import org.example.projectintroproduct.model.PageResponseImpl;
import org.example.projectintroproduct.model.Product;
import org.example.projectintroproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    // danh sách sản phẩm
    @GetMapping
    public String getProducts(Model model,
                              @RequestParam(required = false) String title,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false, defaultValue = "8") int size) {

        PageResponse<Product> pageResponse = PageResponseImpl.<Product>builder()
                .currentPage(page)
                .pageSize(size)
                .data(productService.getAllProducts())
                .build();

        List<Product> newProducts = new ArrayList<Product>();

        if (title != null) {
            newProducts = pageResponse.getContent().stream()
                    .filter(p -> p.getName().toLowerCase().contains(title.toLowerCase()))
                    .toList();
        } else {
            newProducts = pageResponse.getContent();
        }

        model.addAttribute("pageResponse", pageResponse);
        model.addAttribute("products", newProducts);

        System.out.println(productService.getAllProducts().size());
        return "index";
    }

    @GetMapping("/products/{id}")
    public String getProductDetails(Model model, @PathVariable int id) {
        Product product = productService.getAllProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("product", product);
        return "product-detail";
    }
}
