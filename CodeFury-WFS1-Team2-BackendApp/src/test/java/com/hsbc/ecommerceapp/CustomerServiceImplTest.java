package com.hsbc.ecommerceapp;

import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.CustomerService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.service.impl.CustomerServiceImpl;
import com.hsbc.ecommerceapp.service.impl.SubscriptionServiceImpl;
import com.hsbc.ecommerceapp.storage.OrderStorage;
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceImplTest {
    private static SubscriptionService subscriptionService;
    private static CustomerService customerService;

    // initializing before all tests
    @BeforeAll
    public static void setUp() {
        subscriptionService = new SubscriptionServiceImpl(new SubscriptionStorage(), new OrderStorage());
        OrderStorage orderStorage = new OrderStorage();
        customerService = new CustomerServiceImpl(subscriptionService, orderStorage);
    }

    // testing place order
    @Test
    public void testPlaceOrder() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        customerService.placeOrder(subscription);
        Subscription fetchedSubscription = subscriptionService.getSubscriptionById("Subscription1");
        assertNotNull(fetchedSubscription);
        assertEquals("Customer1", fetchedSubscription.getCustomerId());
    }

    // testing cancel order
    @Test
    public void testCancelOrder() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        customerService.placeOrder(subscription);
        customerService.cancelOrder("Subscription1");
        Subscription canceledSubscription = subscriptionService.getSubscriptionById("Subscription1");
        assertFalse(canceledSubscription.isActive());
    }

    // testing view order
    @Test
    public void testViewOrders() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer2", "Bi-Weekly", LocalDate.parse("2024-02-02"), LocalDate.parse("2024-03-02"), true);
        customerService.placeOrder(subscription1);
        customerService.placeOrder(subscription2);
        List<Subscription> orders = customerService.viewOrder("Customer1");
        assertEquals(2, orders.size());
    }

    // testing change subscription plan
    @Test
    public void testChangeSubscriptionPlan() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        customerService.placeOrder(subscription);
        customerService.changeSubscriptionPlan("Subscription1", "Monthly");
        Subscription updatedSubscription = subscriptionService.getSubscriptionById("Subscription1");
        assertEquals("Monthly", updatedSubscription.getType());
    }
}
