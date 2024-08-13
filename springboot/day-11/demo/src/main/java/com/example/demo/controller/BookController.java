package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

// Controller: Nơi đầu tiên tiếp nhận các request và client, xử lý và trả về response
// @ RestController: trả về dữ liệu dạng JSON, XML, ...
// @Controller: trả về giao diện (Template), ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
//@RestController: @Controller + @ResponseBody
// @ResponseBody: trả về dữ liệu dạng JSON, XML,... không trả về template
// @Controller: trả về giao diện (Template), ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
// ResponseEntity<?>: là 1 class đại diện cho response, có thể set status code, header...

//@Controller
//@RestController
@Controller

public class BookController {
    @Autowired
    private BookService bookService;

    // Lấy danh sách tất cả
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBook());
    }

    // Lấy sách theo id
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = bookService.findBookById(id);
        if(book == null) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
    }

   // Viết API theo năm xuất bản
   // http://localhost:8080/books/sortByYear
    @GetMapping("books/sortByYear")
    public ResponseEntity<List<Book>> sortByYear(){
        return ResponseEntity.ok(bookService.sortByYear());
    }


    // Tìm kiếm danh sách theo tên
    // http://localhost:8080/books/searchTitle/{keyword}
    @GetMapping("books/searchTitle/{keyword}")
    public ResponseEntity<List<Book>> searchTitle(@PathVariable String keyword) {
        return ResponseEntity.ok(bookService.findByTitle(keyword));
    }

    // Tìm kiếm sách trong được xuất bản trong khoảng thời gian (fromYear, toYear)
    // http://localhost:8080/books/seachByYear/fromYear/{fromYear}/toYear/{toYear}
    @GetMapping("books/searchByYear/fromYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> searchByYear(@PathVariable int fromYear, @PathVariable int toYear) {
        return ResponseEntity.ok(bookService.findBookBeetweenYear(fromYear, toYear));
    }
}
