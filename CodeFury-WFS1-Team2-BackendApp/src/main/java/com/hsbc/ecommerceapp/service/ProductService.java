package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String product);
    Product getProductById(String productId);
    List<Product> getAllProducts();
}
