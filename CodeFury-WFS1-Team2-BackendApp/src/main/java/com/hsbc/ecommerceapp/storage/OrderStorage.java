package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStorage {
    private Map<String, List<Subscription>> orderMap = new HashMap<>();

    public void addOrder(String customerId, Subscription subscription) {
        List<Subscription> customerOrders = orderMap.getOrDefault(customerId, new ArrayList<>());

        customerOrders.add(subscription);

        orderMap.put(customerId, customerOrders);
    }

    public List<Subscription> getOrderByCustomerId(String customerId) {
        return orderMap.getOrDefault(customerId, new ArrayList<>());
    }

    public void cancelOrder(String customerId, String subscriptionId) {
        List<Subscription> customerOrders = orderMap.get(customerId);
        if(customerOrders != null)
            customerOrders.removeIf(subscription -> subscription.getSubscriptionId().equals(subscriptionId));
    }
}
