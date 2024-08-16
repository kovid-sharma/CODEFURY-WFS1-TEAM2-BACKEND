package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.service.ProductService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStorage {
    private Map<String, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        productMap.put(product.getProductId(), product);
    }

    public void updateProduct(Product product) {
        productMap.put(product.getProductId(), product);
    }

    public void deleteProduct(String productId) {
        productMap.remove(productId);
    }

    public Product getProductById(String productId) {
        return productMap.get(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }
}
