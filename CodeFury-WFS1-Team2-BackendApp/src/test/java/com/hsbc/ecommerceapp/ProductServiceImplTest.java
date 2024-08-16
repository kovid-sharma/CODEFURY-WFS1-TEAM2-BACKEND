package com.hsbc.ecommerceapp;

import com.hsbc.ecommerceapp.exceptions.ProductNotFoundException;
import com.hsbc.ecommerceapp.model.Product;
import com.hsbc.ecommerceapp.service.impl.ProductServiceImpl;
import com.hsbc.ecommerceapp.storage.ProductStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceImplTest {
    private static ProductStorage productStorage = null;
    private static ProductServiceImpl productService = null;

    // initializing before all tests
    @BeforeAll
    public static void setup() {
        productStorage = new ProductStorage();
        productService = new ProductServiceImpl(productStorage);
    }

    // testing add product
    @Test
    public void testAddProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        productService.addProduct(product);
        Product fetchedProduct = productStorage.getProductById("Product1");
        assertNotNull(fetchedProduct);
        assertEquals("Apple", fetchedProduct.getProductName());
    }

    // testing update product
    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        productService.addProduct(product);
        product.setPrice(5.5);
        productService.updateProduct(product);
        Product updatedProduct = productStorage.getProductById("Product1");
        assertEquals(5.5, updatedProduct.getPrice());
    }

    // testing delete product
    @Test
    public void testDeleteProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        productService.addProduct(product);
        productService.deleteProduct("Product1");
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById("Product1"));
    }

    // testing view all products
    @Test
    public void testViewAllProducts() {
        Product product1 = new Product("Product1", "Apple", "Fresh Apple", 3.0, true);
        Product product2 = new Product("Product2", "Banana", "Fresh Banana", 2.0, true);
        productService.addProduct(product1);
        productService.addProduct(product2);
        List<Product> products = productService.getAllProducts();
        assertEquals(2, products.size());
    }
}
