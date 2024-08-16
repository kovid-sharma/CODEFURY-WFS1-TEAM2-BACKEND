package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.model.Customer;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.CustomerService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.storage.OrderStorage;
import com.hsbc.ecommerceapp.storage.UserStorage;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private SubscriptionService subscriptionService;
    private OrderStorage orderStorage;
    private UserStorage userStorage;

    public CustomerServiceImpl(SubscriptionService subscriptionService, OrderStorage orderStorage) {
        this.subscriptionService = subscriptionService;
        this.orderStorage = orderStorage;
    }

    @Override
    public void placeOrder(Subscription subscription) {
        subscriptionService.addSubscription(subscription);
    }

    @Override
    public void cancelOrder(String subscriptionId) {
        subscriptionService.cancelSubscription(subscriptionId);
    }

    @Override
    public void updateCustomerInfo(String customerId, Customer updatedCustomer) throws UserNotFoundException, InvalidInputException {
        // validate the updated customer information
        if(updatedCustomer == null || customerId == null || customerId.isEmpty())
            throw new InvalidInputException("Invalid input for customer update");

        // retrieve existing customer from storage
        Customer existingCustomer = (Customer) userStorage.getUserById(customerId);

        // update existing customer's details with new information
        existingCustomer.setUserName(updatedCustomer.getUserName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());

        // save updated customer back to storage
        userStorage.updateUser(existingCustomer);
    }

    @Override
    public void changeSubscriptionPlan(String subscriptionId, String newPlan) {
        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
        subscription.setType(newPlan);
        subscriptionService.updateSubscription(subscription);
    }

    @Override
    public List<Subscription> viewOrder(String customerId) {
        return orderStorage.getOrderByCustomerId(customerId);
    }
}
