package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionStorage {
    private Map<String, Subscription> subscriptionMap = new HashMap<>();

    public void addSubscription(Subscription subscription) {
        subscriptionMap.put(subscription.getSubscriptionId(), subscription);
    }

    public void updateSubscription(Subscription subscription) {
        subscriptionMap.put(subscription.getSubscriptionId(), subscription);
    }

    public void cancelSubscription(String subscriptionId) {
        Subscription subscription = subscriptionMap.get(subscriptionId);
        if(subscription != null)
            subscription.setActive(false);
    }

    public Subscription getSubscriptionById(String subscriptionId) {
        return subscriptionMap.get(subscriptionId);
    }

    public List<Subscription> getAllSubscriptions() {
        return new ArrayList<>(subscriptionMap.values());
    }
}
