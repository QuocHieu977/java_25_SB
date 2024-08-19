package org.example.projectintroproduct.dao;

import org.example.projectintroproduct.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductDAO {
    List<Product> getProducts();
}
