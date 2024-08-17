package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.exceptions.SubscriptionNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.service.AdminService;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
<<<<<<< HEAD
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;
=======
import com.hsbc.ecommerceapp.storage.ProductStorage;
>>>>>>> 997695be8ed79b1d531aac5c7b27c13152540097

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private ProductService productService;
    private SubscriptionService subscriptionService;
    private SubscriptionStorage subscriptionStorage;

    public AdminServiceImpl(ProductService productService, SubscriptionService subscriptionService) {
        this.productService = productService;
        this.subscriptionService = subscriptionService;
    }

    public AdminServiceImpl(ProductStorage productStorage) {
    }

    @Override
    public void addProduct(User user, Product product) {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");

        productService.addProduct(product);
    }

    @Override
    public void updateProduct(User user, Product product) {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");
        productService.updateProduct(product);
    }

    @Override
    public void deleteProduct(User user, String productId) throws ProductNotFoundException {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");
        productService.deleteProduct(productId);
    }

    @Override
    public void deactivateSubscription(User user, String subscriptionId) throws SubscriptionNotFoundException {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");
        Subscription subscription = subscriptionStorage.getSubscriptionById(subscriptionId);
        if (subscription == null)
            throw new SubscriptionNotFoundException("Subscription not found");
        subscription.setActive(false);
        subscriptionStorage.updateSubscription(subscription);
    }

    @Override
    public void activateSubscription(User user, String subscriptionId) {
        if (!user.isAdmin())
            throw new SecurityException("User does not have admin privileges.");
        Subscription subscription = subscriptionStorage.getSubscriptionById(subscriptionId);
        if (subscription == null)
            throw new SubscriptionNotFoundException("Subscription not found");
        subscription.setActive(true);
        subscriptionStorage.updateSubscription(subscription);
    }

    @Override
    public List<Product> viewAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public List<Subscription> viewAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }
}
