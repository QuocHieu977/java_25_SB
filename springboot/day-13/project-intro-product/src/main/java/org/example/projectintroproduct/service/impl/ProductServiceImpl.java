package org.example.projectintroproduct.service.impl;

import org.example.projectintroproduct.dao.ProductDAO;
import org.example.projectintroproduct.model.Product;
import org.example.projectintroproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getProducts();
    }

    @Override
    public List<Product> searchByTitleProduct(String title) {
        return productDAO.filterProducts(title);
    }

    @Override
    public List<Product> sortByPriceByProduct(String sort) {
        return productDAO.sortProducts(sort);
    }
}
