package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.storage.ProductStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    private ProductStorage productStorage;
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        productStorage = mock(ProductStorage.class);
        productService = new ProductServiceImpl(productStorage);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);

        productService.addProduct(product);

        verify(productStorage).addProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);

        when(productStorage.getProductById("Product1")).thenReturn(product);

        product.setPrice(5.5);
        productService.updateProduct(product);

        verify(productStorage).updateProduct(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);

        when(productStorage.getProductById("Product1")).thenReturn(product);

        productService.deleteProduct("Product1");

        verify(productStorage).deleteProduct("Product1");
    }

    @Test
    public void testViewAllProducts() {
        Product product1 = new Product("Product1", "Apple", "Fresh Apple", 3.0, true);
        Product product2 = new Product("Product2", "Banana", "Fresh Banana", 2.0, true);

        when(productStorage.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productStorage).getAllProducts();
    }
}
