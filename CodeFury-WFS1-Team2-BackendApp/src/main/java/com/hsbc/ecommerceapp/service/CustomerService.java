package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.model.Customer;
import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.model.User;

import java.util.List;

public interface CustomerService {
    void placeOrder(User user, Order order);
    void cancelOrder(String orderId, Order order);
    void updateCustomerInfo(String customerId, Customer updatedCustomer) throws UserNotFoundException, InvalidInputException;
    void changeSubscriptionPlan(String subscriptionId, String newPlan);
    List<Order> viewOrder(String customerId);
}
