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
}
