package com.hsbc.ecommerceapp;

import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.impl.SubscriptionServiceImpl;
import com.hsbc.ecommerceapp.storage.OrderStorage;
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SubscriptionServiceImplTest {

    @Mock
    private SubscriptionStorage subscriptionStorage;

    @Mock
    private OrderStorage orderStorage;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);

        subscriptionService.addSubscription(subscription);

        verify(subscriptionStorage, times(1)).addSubscription(subscription);
        verify(orderStorage, times(1)).addOrder("Customer1", subscription);
    }

    @Test
    public void testUpdateSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        when(subscriptionStorage.getSubscriptionById("Subscription1")).thenReturn(subscription);

        subscription.setType("Monthly");
        subscriptionService.updateSubscription(subscription);

        verify(subscriptionStorage, times(1)).updateSubscription(subscription);
    }

    @Test
    public void testCancelSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        when(subscriptionStorage.getSubscriptionById("Subscription1")).thenReturn(subscription);

        subscriptionService.cancelSubscription("Subscription1");

        verify(subscriptionStorage, times(1)).cancelSubscription("Subscription1");
    }

    @Test
    public void testGetSubscriptionById() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        when(subscriptionStorage.getSubscriptionById("Subscription1")).thenReturn(subscription);

        Subscription fetchedSubscription = subscriptionService.getSubscriptionById("Subscription1");

        assertNotNull(fetchedSubscription);
        assertEquals("Customer1", fetchedSubscription.getCustomerId());
    }

    @Test
    public void testGetAllSubscriptions() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer2", "Bi-Weekly", LocalDate.parse("2024-02-02"), LocalDate.parse("2024-03-02"), true);
        when(subscriptionStorage.getAllSubscriptions()).thenReturn(Arrays.asList(subscription1, subscription2));

        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

        assertEquals(2, subscriptions.size());
        verify(subscriptionStorage, times(1)).getAllSubscriptions();
    }
}
