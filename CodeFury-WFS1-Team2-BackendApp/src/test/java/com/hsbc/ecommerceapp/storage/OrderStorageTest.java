package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Order;
import com.hsbc.ecommerceapp.model.Subscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStorageTest {

    private OrderStorage orderStorage;
    private Order order = null;

    @BeforeEach
    public void setup() {
        orderStorage = new OrderStorage();
        order = new Order("Order1", "Customer1", 100.5, "delivered");
    }

    @Test
    public void testAddOrder() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        orderStorage.addOrder("Customer1", order);

        List<Order> orders = orderStorage.getOrderByCustomerId("Customer1");
        assertEquals(1, orders.size());
        assertEquals(subscription, orders.get(0));
    }

    @Test
    public void testGetOrderByCustomerId() {
        Order order1 = new Order("Order1", "Customer1", 100.5, "delivered");
        Order order2 = new Order("Order2", "Customer2", 200.5, "delivered");

        orderStorage.addOrder("Customer1", order1);
        orderStorage.addOrder("Customer1", order2);

        List<Subscription> orders = (List<Subscription>) orderStorage.getOrderById("Customer1");
        assertEquals(2, orders.size());
    }

    @Test
    public void testCancelOrder() {
        orderStorage.addOrder("Customer1", order);

        orderStorage.deleteOrder("Customer1");
        List<Order> orders = orderStorage.getOrderByCustomerId("Customer1");
        assertTrue(orders.isEmpty());
    }
}
