package org.example.projectintroproduct.database;

import org.example.projectintroproduct.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitProduct implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Đọc dữ liệu từ file...");
        ProductDB.products = fileReader.readFile("products.json");
    }
}
