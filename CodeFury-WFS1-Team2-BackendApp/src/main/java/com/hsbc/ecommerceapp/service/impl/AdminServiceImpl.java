package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.AdminService;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.service.SubscriptionService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private ProductService productService;
    private SubscriptionService subscriptionService;

    public AdminServiceImpl(ProductService productService, SubscriptionService subscriptionService) {
        this.productService = productService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void addProduct(Product product) {
        productService.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    @Override
    public void deleteProduct(String productId) {
        productService.deleteProduct(productId);
    }

    @Override
    public void deactivateSubscription(String subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
    }

    @Override
    public void activateSubscription(String subscriptionId) {
        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
        subscription.setActive(true);
        subscriptionService.updateSubscription(subscription);
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
