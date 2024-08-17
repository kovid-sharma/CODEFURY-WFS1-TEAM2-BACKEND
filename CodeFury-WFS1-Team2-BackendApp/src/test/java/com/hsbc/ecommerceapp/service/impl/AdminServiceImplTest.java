package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.service.ProductService;
import com.hsbc.ecommerceapp.service.SubscriptionService;
import com.hsbc.ecommerceapp.storage.ProductStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdminServiceImplTest {

    private AdminServiceImpl adminService;
    private ProductStorage productStorage;
    private ProductService productService;
    private SubscriptionService subscriptionService;
    private User user;

    @BeforeEach
    public void setup() {
        // Mocking the ProductStorage dependency
//        productStorage = mock(ProductStorage.class);
        productService = mock(ProductService.class);
        // Injecting the mock into the AdminServiceImpl
//        adminService = new AdminServiceImpl(productStorage);
        adminService = new AdminServiceImpl(productService, subscriptionService);

        user = user = new User("User1", "john_wick", "password123", "john@wick.com", "customer");
    }

    @Test
    public void testAddProduct() {
        // Arrange
        Product product = new Product("Product1", "Laptop", "High-end gaming laptop", 1200.0, true);

        // Act
        adminService.addProduct(user, product);

        // Assert - Verify that addProduct was called once with the correct product
        verify(productStorage, times(1)).addProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Product product = new Product("Product1", "Laptop", "High-end gaming laptop", 1200.0, true);

        // Stubbing the getProductById method to return the product when called
        when(productStorage.getProductById("Product1")).thenReturn(product);

        // Act
        product.setPrice(1300.0);
        adminService.updateProduct(user, product);

        // Assert - Verify that updateProduct was called with the updated product
        verify(productStorage, times(1)).updateProduct(product);
    }

    @Test
    public void testDeleteProduct() {
        // Arrange
        Product product = new Product("Product1", "Laptop", "High-end gaming laptop", 1200.0, true);

        // Stubbing the getProductById method to return the product when called
        when(productStorage.getProductById("Product1")).thenReturn(product);

        // Act
        adminService.deleteProduct(user, "Product1");

        // Assert - Verify that deleteProduct was called once with the correct product ID
        verify(productStorage, times(1)).deleteProduct("Product1");
    }

    @Test
    public void testViewAllProducts() {
        // Arrange
        Product product1 = new Product("Product1", "Laptop", "High-end gaming laptop", 1200.0, true);
        Product product2 = new Product("Product2", "Smartphone", "Latest model", 800.0, true);

        // Stubbing the getAllProducts method to return a list of products
        when(productStorage.getAllProducts()).thenReturn(List.of(product1, product2));

        // Act
        List<Product> products = adminService.viewAllProducts();

        // Assert
        assertEquals(2, products.size());
        verify(productStorage, times(1)).getAllProducts();
    }
}
