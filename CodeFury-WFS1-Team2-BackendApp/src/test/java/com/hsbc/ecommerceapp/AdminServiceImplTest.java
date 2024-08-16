package com.hsbc.ecommerceapp;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.Subscription;
import com.hsbc.ecommerceapp.service.AdminService;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.service.impl.AdminServiceImpl;
import com.hsbc.ecommerceapp.service.impl.ProductServiceImpl;
import com.hsbc.ecommerceapp.service.impl.SubscriptionServiceImpl;
import com.hsbc.ecommerceapp.storage.ProductStorage;
import com.hsbc.ecommerceapp.storage.SubscriptionStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminServiceImplTest {
    private static AdminService adminService;
    private static ProductStorage productStorage;
    private static SubscriptionStorage subscriptionStorage;
    private static ProductService productService;
    private static SubscriptionService subscriptionService;

    // initializing before all tests
    @BeforeAll
    public static void setup() {
        productStorage = new ProductStorage();
        subscriptionStorage = new SubscriptionStorage();
        productService = new ProductServiceImpl(productStorage);
        subscriptionService = new SubscriptionServiceImpl(subscriptionStorage, null);
        adminService = new AdminServiceImpl(productService, subscriptionService);
    }

    // testing add product
    @Test
    public void testAddProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        adminService.addProduct(product);
        Product fetchedProduct = productService.getProductById("Product1");
        assertNotNull(fetchedProduct);
        assertEquals("Apple", fetchedProduct.getProductName());
    }

    // testing update product
    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        adminService.addProduct(product);
        product.setPrice(5.5);
        adminService.updateProduct(product);
        Product updatedProduct = productService.getProductById("Product1");
        assertEquals(3.5, updatedProduct.getPrice());
    }

    // testing delete product
    @Test
    public void testDeleteProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        adminService.addProduct(product);
        adminService.deleteProduct("Product1");
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("1"));
    }

    // testing view all subscriptions
    @Test
    public void testViewAllProducts() {
        Product product1 = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        Product product2 = new Product("Product2", "Banana", "Fresh Banana", 3.0, true);
        adminService.addProduct(product1);
        adminService.addProduct(product2);
        List<Product> products = adminService.viewAllProducts();
        assertEquals(2, products.size());
    }

    // test delete subscription
    @Test
    public void testDeactivateSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        adminService.activateSubscription("Subscription1");
        adminService.deactivateSubscription("Subscription1");
        Subscription fetchedSubscription = subscriptionService.getSubscriptionById("Subscription1");
        assertFalse(fetchedSubscription.isActive());
    }

    // testing subscription activation
    @Test
    public void testActivateSubscription() {
        Subscription subscription = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        subscriptionService.addSubscription(subscription);
        adminService.activateSubscription("Subscription1");
        Subscription fetchedSubscription = subscriptionService.getSubscriptionById("Subscription1");
        assertTrue(fetchedSubscription.isActive());
    }

    // testing view all subscription
    @Test
    public void testViewAllSubscriptions() {
        Subscription subscription1 = new Subscription("Subscription1", "Product1", "Customer1", "Weekly", LocalDate.parse("2024-01-01"), LocalDate.parse("2024-02-01"), true);
        Subscription subscription2 = new Subscription("Subscription2", "Product2", "Customer2", "Bi-Weekly", LocalDate.parse("2024-02-02"), LocalDate.parse("2024-03-02"), true);
        subscriptionService.addSubscription(subscription1);
        subscriptionService.addSubscription(subscription2);
        List<Subscription> subscriptions = adminService.viewAllSubscriptions();
        assertEquals(2, subscriptions.size());
    }


}
