package org.example.projectintroproduct.service;


import org.example.projectintroproduct.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> searchByTitleProduct(String title);

    List<Product> sortByPriceByProduct(String sort);
}
