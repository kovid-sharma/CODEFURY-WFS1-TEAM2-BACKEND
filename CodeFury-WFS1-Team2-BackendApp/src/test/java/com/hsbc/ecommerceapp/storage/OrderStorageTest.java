package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStorageTest {

    private OrderStorage orderStorage;

    @BeforeEach
    public void setup() {
        orderStorage = new OrderStorage();
    }

    @Test
    public void testAddOrder() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        orderStorage.addOrder("Customer1", subscription);

        List<Subscription> orders = orderStorage.getOrderByCustomerId("Customer1");
        assertEquals(1, orders.size());
        assertEquals(subscription, orders.get(0));
    }

    @Test
    public void testGetOrderByCustomerId() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer1", "Monthly", LocalDate.parse("2024-02-01"), LocalDate.parse("2024-03-01"), true);

        orderStorage.addOrder("Customer1", subscription1);
        orderStorage.addOrder("Customer1", subscription2);

        List<Subscription> orders = orderStorage.getOrderByCustomerId("Customer1");
        assertEquals(2, orders.size());
    }

    @Test
    public void testCancelOrder() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        orderStorage.addOrder("Customer1", subscription);

        orderStorage.cancelOrder("Customer1", "Subscription1");
        List<Subscription> orders = orderStorage.getOrderByCustomerId("Customer1");
        assertTrue(orders.isEmpty());
    }
}
