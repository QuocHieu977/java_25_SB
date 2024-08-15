package com.example.demo_thymeleaf.controller;

import com.example.demo_thymeleaf.model.Book;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

// Controller: Nơi đầu tiên tiếp nhận các request và client, xử lý và trả về response
// @ RestController: trả về dữ liệu dạng JSON, XML, ...
// @Controller: trả về giao diện (Template), ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
@Controller

public class BookController {
    List<Book> books = new ArrayList<>(List.of(
            new Book(1, "sach 1", "author_3", 1991),
            new Book(2, "sach 2", "author_2", 1991),
            new Book(3, "sach 3", "author_1", 1991)
    ));

    // hiện thị template
    @GetMapping
    public String getHomePage(Model model){
        Book book = books.get(0);
        model.addAttribute("book", book);
        return "index";
    }

    @GetMapping("/blog")
    public String getPage(){
        return "admin/blog";
    }
}
