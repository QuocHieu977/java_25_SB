package org.example.projectintroproduct.dao.impl;

import org.example.projectintroproduct.dao.ProductDAO;
import org.example.projectintroproduct.database.ProductDB;
import org.example.projectintroproduct.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {

    @Override
    public List<Product> getProducts() {
        return ProductDB.products;
    }

    @Override
    public List<Product> filterProducts(String title) {
        return ProductDB.products.stream()
                .filter(product -> product.getName().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

    @Override
    public List<Product> sortProducts(String sort) {
        if (sort.equalsIgnoreCase("desc")) {
            return ProductDB.products.stream()
                    .sorted((o1, o2) -> o1.getPrice() - o2.getPrice())
                    .toList();
        } else if (sort.equalsIgnoreCase("asc")) {
            return ProductDB.products.stream()
                    .sorted((o1, o2) -> o2.getPrice() - o1.getPrice())
                    .toList();
        } else {
            return ProductDB.products;
        }
    }
}
