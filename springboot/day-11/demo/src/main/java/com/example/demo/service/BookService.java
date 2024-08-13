package com.example.demo.service;

import com.example.demo.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book findBookById(int id);

    List<Book> findByTitle(String keyword);

    List<Book> sortByYear();

    List<Book> findBookBeetweenYear(int startYear, int endYear);
}
