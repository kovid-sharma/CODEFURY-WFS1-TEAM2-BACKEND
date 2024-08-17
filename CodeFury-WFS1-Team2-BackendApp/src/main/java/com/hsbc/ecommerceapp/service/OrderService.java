package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.exceptions.OrderNotFoundException;
import com.hsbc.ecommerceapp.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(String customerId, Order order);
    void updateOrder(String customerId, Order order) throws OrderNotFoundException;
    void cancelOrder(String customerId, Order order) throws OrderNotFoundException;
    void deleteOrder(String orderId) throws OrderNotFoundException;
    Order getOrderById(String orderId) throws OrderNotFoundException;
}
