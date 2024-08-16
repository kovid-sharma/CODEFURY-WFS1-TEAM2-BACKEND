package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.storage.ProductStorage;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductStorage productStorage;

    public ProductServiceImpl(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    @Override
    public void addProduct(Product product) {
        productStorage.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        if(productStorage.getProductById(product.getProductId()) == null)
            throw new ProductNotFoundException("Product not found!");
        productStorage.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        if (productStorage.getProductById(productId) == null)
            throw new ProductNotFoundException("Product not found!");
        productStorage.deleteProduct(productId);
    }

    @Override
    public Product getProductById(String productId) {
        Product product = productStorage.getProductById(productId);
        if(product == null)
            throw new ProductNotFoundException("Product not found!");
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productStorage.getAllProducts();
    }
}
