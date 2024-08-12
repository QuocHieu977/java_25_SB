package com.example.demo.controller;

import com.example.demo.model.Book;
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
    List<Book> books = new ArrayList<>(List.of(
            new Book(1, "sach trinh tham 1", "đâs", 1991),
            new Book(2, "sach dien tư 2", "đâs", 1990),
            new Book(3, "sach trinh tham 3", "đâs", 1995)
    ));

    // Lấy danh sách tất cả
//    @GetMapping("/books") // GET: http://localhost:8080/books
//    @ResponseBody // dữ liệu return sẽ nằm trong body của response
//    @ResponseStatus(HttpStatus.ACCEPTED) // 202
//    public List<Book> getAllBooks() {
//        return books;
//    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }


    // Lấy sách theo id
//    // http://localhost:8080/books/1
//    @GetMapping("/books/{id}")
//    @ResponseBody
//    public Book getBookById(@PathVariable int id) { // PathVariable được sử dụng lấy giá trị linh động của id
//        System.out.println("id: " + id);
//        for(Book book: books) {
//            if(book.getId() == id) {
//                return book;
//            }
//        }
//        return null;
//    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        System.out.println("id: " + id);
        for(Book book: books) {
            if(book.getId() == id) {
                return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); /// 404
    }

    // Viết API theo năm xuất bản
    // http://localhost:8080/books/sortByYear
    @GetMapping("books/sortByYear")
    public ResponseEntity<List<Book>> sortByYear(){
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getYear() - o2.getYear();
            }
        });
        return ResponseEntity.ok(books);
    }

    // Tìm kiếm danh sách theo tên
    // http://localhost:8080/books/searchTitle/{keyword}
    @GetMapping("books/searchTitle/{keyword}")
    public ResponseEntity<List<Book>> searchTitle(@PathVariable String keyword) {
        List<Book> newBook = new ArrayList<>();
        for(Book book: books) {
            if(book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                    newBook.add(book);
            }
        }
        return ResponseEntity.ok(newBook);
    }

    // Tìm kiếm sách trong được xuất bản trong khoảng thời gian (fromYear, toYear)
    // http://localhost:8080/books/seachByYear/fromYear/{fromYear}/toYear/{toYear}
    @GetMapping("books/seachByYear/fromYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> searchByYear(@PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> newBook = new ArrayList<>();
        for(Book book: books) {
            if(book.getYear() >= fromYear && book.getYear() <= toYear) {
                newBook.add(book);
            }
        }
        return ResponseEntity.ok(newBook);
    }
}
