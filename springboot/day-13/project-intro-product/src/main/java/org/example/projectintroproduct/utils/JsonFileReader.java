package org.example.projectintroproduct.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.projectintroproduct.model.Product;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonFileReader implements IFileReader {

    @Override
    public List<Product> readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> data = new ArrayList<>();

        try {
            // Đọc file JSON và chuyển đổi dữ liệu thành danh sách Book
            data = objectMapper.readValue(
                    new File(path),
                    new TypeReference<List<Product>>() {}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
