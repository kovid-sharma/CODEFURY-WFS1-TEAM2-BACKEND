package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionStorageTest {

    private SubscriptionStorage subscriptionStorage;

    @BeforeEach
    public void setup() {
        subscriptionStorage = new SubscriptionStorage();
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionStorage.addSubscription(subscription);

        Subscription fetchedSubscription = subscriptionStorage.getSubscriptionById("Subscription1");
        assertNotNull(fetchedSubscription);
        assertEquals("Customer1", fetchedSubscription.getCustomerId());
    }

    @Test
    public void testUpdateSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionStorage.addSubscription(subscription);
        subscription.setType("Monthly");
        subscriptionStorage.updateSubscription(subscription);

        Subscription updatedSubscription = subscriptionStorage.getSubscriptionById("Subscription1");
        assertEquals("Monthly", updatedSubscription.getType());
    }

    @Test
    public void testCancelSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionStorage.addSubscription(subscription);
        subscriptionStorage.cancelSubscription("Subscription1");

        Subscription canceledSubscription = subscriptionStorage.getSubscriptionById("Subscription1");
        assertFalse(canceledSubscription.isActive());
    }

    @Test
    public void testGetAllSubscriptions() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer2", "Monthly", LocalDate.parse("2024-02-01"), LocalDate.parse("2024-03-01"), true);

        subscriptionStorage.addSubscription(subscription1);
        subscriptionStorage.addSubscription(subscription2);

        List<Subscription> subscriptions = subscriptionStorage.getAllSubscriptions();
        assertEquals(2, subscriptions.size());
    }
}
