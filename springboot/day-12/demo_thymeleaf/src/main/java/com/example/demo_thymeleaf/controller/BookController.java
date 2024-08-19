package com.example.demo_thymeleaf.controller;

import com.example.demo_thymeleaf.model.Book;
import com.example.demo_thymeleaf.model.PageResponse;
import com.example.demo_thymeleaf.model.PageResponseImpl;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

// Controller: Nơi đầu tiên tiếp nhận các request và client, xử lý và trả về response
// @ RestController: trả về dữ liệu dạng JSON, XML, ...
// @Controller: trả về giao diện (Template), ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
@Controller

public class BookController {
    List<Book> books = new ArrayList<>();

    public BookController() {
        Faker faker = new Faker();
        int numberOfAuthors = 10;  // Số lượng tác giả khác nhau
        List<String> authors = new ArrayList<>();

        // Tạo một danh sách các tác giả
        for (int i = 0; i < numberOfAuthors; i++) {
            authors.add(faker.book().author());
        }

        // Tạo dữ liệu sách giả với cùng tác giả
        for (int i = 0; i <= 50; i++) {
            // Chọn một tác giả ngẫu nhiên từ danh sách
            String author = authors.get(faker.number().numberBetween(0, numberOfAuthors));

            // Tạo ngày ngẫu nhiên trong quá khứ (ví dụ: trong vòng 10 năm qua)
            LocalDate createdDate = faker.date().past(3650, TimeUnit.DAYS).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();


            Book book = Book.builder()
                    .id(i)
                    .title(faker.book().title())
                    .author(author)
                    .year(faker.number().numberBetween(1950, 2020))
                    .createDate(createdDate)
                    .build();

            books.add(book);
        }
    }
    // hiện thị template
    @GetMapping
    public String getHomePage(Model model, @RequestParam(required = false) String title){
//        Book book = books.get(0);
//        model.addAttribute("book", book);

        List<Book> newBook = new ArrayList<>();
        if (title != null) {
            newBook = books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .toList();
        } else {
            newBook = books;
        }

        model.addAttribute("books", newBook);
        return "index";
    }

    // phân trang
    // currentPage: trang hiện tại
    // pageSize: số lượng phần tử trên một trang
    // totalElements: tổng số phần tử
    // totalPages: tổng số lương
    // content: dữ liện trang hiện tại
    @GetMapping("/books")
    public String getBooksPage(Model model,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "10") int size) {

        PageResponse<Book> pageResponse = PageResponseImpl.<Book>builder()
                .currentPage(page)
                .pageSize(size)
                .data(books)
                .build();

        model.addAttribute("pageResponse", pageResponse);
        return "books";
    }

    @GetMapping("/book/{id}")
    public  String getBookDetails(@PathVariable int id, Model model){

        // Xem chi tiết cuốn sách hiên tại
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);


        // Lấy các cuốn sác liên quan
        List<Book> relateBooks = books.stream()
                    .filter(b -> book.getAuthor().equals(b.getAuthor()))
                    .filter(b -> b.getId() != id)
                    .sorted(Comparator.comparing(Book::getCreateDate).reversed())
                    .limit(4)
                    .toList();

        model.addAttribute("book", book);
        model.addAttribute("relateBooks", relateBooks);
        System.out.println(relateBooks);
        return "book-detail";
    }

    @GetMapping("/blog")
    public String getPage(){
        return "admin/blog";
    }
}

