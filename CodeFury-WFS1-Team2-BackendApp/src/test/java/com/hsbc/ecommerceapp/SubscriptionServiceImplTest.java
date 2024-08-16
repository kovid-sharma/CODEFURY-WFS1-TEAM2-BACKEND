package com.hsbc.ecommerceapp;

import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.impl.SubscriptionServiceImpl;
import com.hsbc.ecommerceapp.storage.OrderStorage;
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionServiceImplTest {
    private static SubscriptionStorage subscriptionStorage;
    private static OrderStorage orderStorage;
    private static SubscriptionServiceImpl subscriptionService;

    // initializing before all tests
    @BeforeAll
    public static void setup() {
        subscriptionStorage = new SubscriptionStorage();
        orderStorage = new OrderStorage();
        subscriptionService = new SubscriptionServiceImpl(subscriptionStorage, orderStorage);
    }

    // testing add subscription
    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionService.addSubscription(subscription);
        Subscription fetchedSubscription = subscriptionStorage.getSubscriptionById("1");
        assertNotNull(fetchedSubscription);
        assertEquals("Weekly", fetchedSubscription.getType());
    }

    // testing update subscription
    @Test
    public void testUpdateSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionService.addSubscription(subscription);
        subscription.setType("Monthly");
        subscriptionService.updateSubscription(subscription);
        Subscription updatedSubscription = subscriptionStorage.getSubscriptionById("1");
        assertEquals("Monthly", updatedSubscription.getType());
    }

    // testing cancel subscription
    @Test
    public void testCancelSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionService.addSubscription(subscription);
        subscriptionService.cancelSubscription("Subscription1");
        Subscription canceledSubscription = subscriptionStorage.getSubscriptionById("Subscription1");
        assertFalse(canceledSubscription.isActive());
    }

    // testing get subscription by id
    @Test
    public void testGetSubscriptionById() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionService.addSubscription(subscription);
        Subscription fetchedSubscription = subscriptionService.getSubscriptionById("1");
        assertNotNull(fetchedSubscription);
        assertEquals("Customer1", fetchedSubscription.getCustomerId());
    }

    @Test
    public void testGetAllSubscriptions() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription1", "Product2", "Customer2", "Bi-Weekly", LocalDate.parse("2024-02-02"), LocalDate.parse("2024-03-02"), true);
        subscriptionService.addSubscription(subscription1);
        subscriptionService.addSubscription(subscription2);
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
        assertEquals(2, subscriptions.size());
    }


}
