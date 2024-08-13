package com.example.demo.utils;

import com.example.demo.model.Book;

import java.util.List;

public interface IFileReader {
    List<Book> readFile(String path);
}
