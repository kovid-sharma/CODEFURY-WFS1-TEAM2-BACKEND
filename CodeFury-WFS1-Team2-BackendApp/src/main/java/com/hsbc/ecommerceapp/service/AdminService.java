package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;

import java.util.List;

public interface AdminService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String productId);
    void deactivateSubscription(String subscriptionId);
    void activateSubscription(String subscriptionId);
    List<Product> viewAllProducts();
    List<Subscription> viewAllSubscriptions();
}
