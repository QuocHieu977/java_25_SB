package org.example.projectintroproduct.utils;

import org.example.projectintroproduct.model.Product;

import java.util.List;

public interface IFileReader{
    List<Product> readFile(String path);
}
