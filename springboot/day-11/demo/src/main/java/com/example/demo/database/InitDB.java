package com.example.demo.database;

import com.example.demo.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {
    // khi ứng dụng đc chạy thì sẽ khởi tạo database
    // có 3 trường hợp đọc file -> trong trường hợp này cần đọc từ file json -> nên cần @Qualifier("jsonFileReader")≤≥
    @Qualifier("jsonFileReader")
    @Autowired
    private IFileReader iFileReader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Đọc dữ liệu từ file...");

        // lấy ra các cuốn sách gán vào DB
        BookDB.books = iFileReader.readFile("book.json");
        System.out.println(BookDB.books.size());
    }
}
