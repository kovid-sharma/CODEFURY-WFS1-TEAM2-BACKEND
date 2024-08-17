package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.storage.ProductStorage;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductStorage productStorage;

    // constructor
    public ProductServiceImpl(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    // overriding add product
    @Override
    public void addProduct(Product product) {
        productStorage.addProduct(product);
    }

    // overriding update product
    @Override
    public void updateProduct(Product product) {
        if(productStorage.getProductById(product.getProductId()) == null)
            throw new ProductNotFoundException("Product not found!");
        productStorage.updateProduct(product);
    }

    // overriding delete product
    @Override
    public void deleteProduct(String productId) {
        if (productStorage.getProductById(productId) == null)
            throw new ProductNotFoundException("Product not found!");
        productStorage.deleteProduct(productId);
    }

    // overriding get product by id
    @Override
    public Product getProductById(String productId) {
        Product product = productStorage.getProductById(productId);
        if(product == null)
            throw new ProductNotFoundException("Product not found!");
        return product;
    }

    // overriding get all products
    @Override
    public List<Product> getAllProducts() {
        return productStorage.getAllProducts();
    }
}
