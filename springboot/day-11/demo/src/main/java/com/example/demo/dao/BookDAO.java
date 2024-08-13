package com.example.demo.dao;

import com.example.demo.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    Book findById(int id);

    List<Book> findByTitleContainsIgnoreCase(String keyword);

    List<Book> sortByYearPublication();
}
