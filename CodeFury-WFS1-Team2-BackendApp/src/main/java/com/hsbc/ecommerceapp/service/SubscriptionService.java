package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    void addSubscription(Subscription subscription);
    void updateSubscription(Subscription subscription);
    void cancelSubscription(String subscriptionId);
    Subscription getSubscriptionById(String subscriptionId);
    List<Subscription> getAllSubscriptions();
}
