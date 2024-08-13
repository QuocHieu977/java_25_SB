package com.example.demo.service.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBook() {
        return bookDAO.findAll();
    }

    @Override
    public Book findBookById(int id) {
        return bookDAO.findById(id);
    }


    @Override
    public List<Book> findByTitle(String keyword) {
        return bookDAO.findByTitleContainsIgnoreCase(keyword);
    }

    @Override
    public List<Book> sortByYear() {
        return bookDAO.sortByYearPublication();
    }

    @Override
    public List<Book> findBookBeetweenYear(int startYear, int endYear) {
        List<Book> books = bookDAO.findAll();
        List<Book> newBook = new ArrayList<>();
        for(Book book: books) {
            if(book.getYear() >= startYear && book.getYear() <= endYear) {
                newBook.add(book);
            }
        }
        return newBook;
    }
}
