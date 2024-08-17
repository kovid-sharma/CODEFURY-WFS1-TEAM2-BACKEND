package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderStorage {
    private Map<String, List<Product>> orderMap = new HashMap<>();

    public void addOrder(String customerId, Product product) {
        List<Product> customerOrders = orderMap.getOrDefault(customerId, new ArrayList<>());

        customerOrders.add(product);

        orderMap.put(customerId, customerOrders);
    }

    public List<Product> getOrderByCustomerId(String customerId) {
        return orderMap.getOrDefault(customerId, new ArrayList<>());
    }

    public void cancelOrder(String customerId, String productId) {
        List<Product> customerOrders = orderMap.get(customerId);
        if(customerOrders != null)
            customerOrders.removeIf(product -> product.getProductId().equals(productId));
    }
}
