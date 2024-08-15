package com.example.demo.dao.impl;

import com.example.demo.dao.BookDAO;
import com.example.demo.database.BookDB;
import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookDAOImpl implements BookDAO {

    @Override
    public List<Book> findAll() {
        return BookDB.books;
    }

    @Override
    public Book findById(int id) {
        return BookDB.books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null); // nếu không có quyển sách nào sẽ return về null
    }

    @Override
    public List<Book> findByTitleContainsIgnoreCase(String keyword) {
        return BookDB.books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

//        List<Book> result = new ArrayList<>();
//        for(Book book: BookDB.books) { // select
//            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
//                result.add(book);
//            }
//        }
//        return result;
    }

    @Override
    public List<Book> sortByYearPublication() {
        return BookDB.books.stream()
                .sorted((o1, o2) -> o2.getYear() - o1.getYear())
                .collect(Collectors.toList());

//        BookDB.books.sort(new Comparator<Book>() {
//            @Override
//            public int compare(Book o1, Book o2) {
//                return o2.getYear() - o1.getYear();
//            }
//        });
//        return BookDB.books;
    }
}
