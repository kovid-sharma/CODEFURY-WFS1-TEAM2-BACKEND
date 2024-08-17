package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.OrderNotFoundException;
import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.service.OrderService;
import com.hsbc.ecommerceapp.storage.OrderStorage;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderStorage orderStorage;

    public OrderServiceImpl(OrderStorage orderStorage) {
        this.orderStorage = orderStorage;
    }

    @Override
    public void createOrder(String customerId, Order order) {
        orderStorage.addOrder(customerId, order);
    }

    @Override
    public void updateOrder(String customerId, Order order) throws OrderNotFoundException {
        orderStorage.updateOrder(customerId, order);
    }

    @Override
    public void cancelOrder(String customerId, Order order) throws OrderNotFoundException {
        if (order != null) {
            order.setStatus("Cancelled");
            orderStorage.updateOrder(customerId, order);
        } else {
            throw new OrderNotFoundException("Order note found!");
        }
    }

    @Override
    public void deleteOrder(String orderId) throws OrderNotFoundException {
        if(orderStorage.getOrderById(orderId) != null)
            orderStorage.deleteOrder(orderId);
        else
            throw new OrderNotFoundException("Order not found!");
    }


    @Override
    public Order getOrderById(String orderId) throws OrderNotFoundException {
        return orderStorage.getOrderById(orderId);
    }
}
