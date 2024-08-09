package com.example.demo.controller;

import com.example.demo.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// Controller: Nơi đầu tiên tiếp nhận các request và client, xử lý và trả về response
// @ RestController: trả về dữ liệu dạng JSON, XML, ...
// @Controller: trả về giao diện (Template), ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
@RestController

public class BookController {
    List<Book> books = new ArrayList<>(List.of(
            new Book(1, "sach 1", "đâs", 1991),
            new Book(2, "sach 2", "đâs", 1991),
            new Book(3, "sach 3", "đâs", 1991)
    ));

    // Lấy danh sách tất cả
    @GetMapping("/books") // GET: http://localhost:8080/books
    public List<Book> getAllBooks() {
        return books;
    }
    // Lấy sách theo id
    // http://localhost:8080/books/1
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id) { // PathVariable được sử dụng lấy giá trị linh động của id
        System.out.println("id: " + id);
        for(Book book: books) {
            if(book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
