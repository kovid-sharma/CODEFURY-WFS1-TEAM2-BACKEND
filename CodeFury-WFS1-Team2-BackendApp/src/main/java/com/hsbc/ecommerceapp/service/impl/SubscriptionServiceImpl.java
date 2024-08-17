package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.SubscriptionNotFoundException;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.storage.OrderStorage;
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionStorage subscriptionStorage;
    private OrderStorage orderStorage;

    // constructor
    public SubscriptionServiceImpl(SubscriptionStorage subscriptionStorage, OrderStorage orderStorage) {
        this.subscriptionStorage = subscriptionStorage;
        this.orderStorage = orderStorage;
    }

    // overriding add subscription
    @Override
    public void addSubscription(Subscription subscription) {
        subscriptionStorage.addSubscription(subscription);
    }

    // overriding update subscription
    @Override
    public void updateSubscription(Subscription subscription) {
        if (subscriptionStorage.getSubscriptionById(subscription.getSubscriptionId()) == null)
            throw new SubscriptionNotFoundException("Subscription not found!");
        subscriptionStorage.updateSubscription(subscription);
    }

    // overriding cancel subscription
    @Override
    public void cancelSubscription(String subscriptionId) {
        if (subscriptionStorage.getSubscriptionById(subscriptionId) == null)
            throw new SubscriptionNotFoundException("Subscription not found!");
        subscriptionStorage.cancelSubscription(subscriptionId);
    }

    // overriding get subscription by id
    @Override
    public Subscription getSubscriptionById(String subscriptionId) {
        Subscription subscription = subscriptionStorage.getSubscriptionById(subscriptionId);
        if(subscription == null)
            throw new SubscriptionNotFoundException("Subscription not found!");
        return subscription;
    }

    // overriding get all subscription
    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionStorage.getAllSubscriptions();
    }
}
