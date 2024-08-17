package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.model.User;

import java.util.List;

public interface AdminService {
    void addProduct(User user, Product product);
    void updateProduct(User user, Product product);
    void deleteProduct(User user, String productId);
    void deactivateSubscription(User user, String subscriptionId);
    void activateSubscription(User user, String subscriptionId);
    List<Product> viewAllProducts();
    List<Subscription> viewAllSubscriptions();
}
