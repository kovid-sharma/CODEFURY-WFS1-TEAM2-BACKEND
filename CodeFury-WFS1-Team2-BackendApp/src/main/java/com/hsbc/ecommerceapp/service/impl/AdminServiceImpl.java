package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.exceptions.SubscriptionNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.service.AdminService;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private ProductService productService;
    private SubscriptionService subscriptionService;
    private SubscriptionStorage subscriptionStorage;

    // constructor
    public AdminServiceImpl(ProductService productService, SubscriptionService subscriptionService) {
        this.productService = productService;
        this.subscriptionService = subscriptionService;
    }

    // overriding add product
    @Override
    public void addProduct(User user, Product product) {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");

        productService.addProduct(product);
    }

    // overriding update product
    @Override
    public void updateProduct(User user, Product product) {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");
        productService.updateProduct(product);
    }

    // overriding delete product
    @Override
    public void deleteProduct(User user, String productId) throws ProductNotFoundException {
        if(!user.isAdmin())
            throw new SecurityException("Access denied to user!");
        productService.deleteProduct(productId);
    }

    // overriding deactivate subscription
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

    // overriding activate subscription
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

    // overriding view all products
    @Override
    public List<Product> viewAllProducts() {
        return productService.getAllProducts();
    }

    // overriding view all subscriptions
    @Override
    public List<Subscription> viewAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }
}
