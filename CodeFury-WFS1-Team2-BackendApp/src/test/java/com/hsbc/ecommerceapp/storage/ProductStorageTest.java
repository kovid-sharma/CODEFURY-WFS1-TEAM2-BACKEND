package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductStorageTest {

    private ProductStorage productStorage;

    @BeforeEach
    public void setup() {
        productStorage = new ProductStorage();
    }

    @Test
    public void testAddProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        productStorage.addProduct(product);

        Product fetchedProduct = productStorage.getProductById("Product1");
        assertNotNull(fetchedProduct);
        assertEquals("Apple", fetchedProduct.getProductName());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        productStorage.addProduct(product);
        product.setPrice(5.5);
        productStorage.updateProduct(product);

        Product updatedProduct = productStorage.getProductById("Product1");
        assertEquals(5.5, updatedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        productStorage.addProduct(product);
        productStorage.deleteProduct("Product1");

        Product fetchedProduct = productStorage.getProductById("Product1");
        assertNull(fetchedProduct);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product("Product1", "Apple", "Fresh Apple", 5.0, true);
        Product product2 = new Product("Product2", "Banana", "Fresh Banana", 2.0, true);

        productStorage.addProduct(product1);
        productStorage.addProduct(product2);

        List<Product> products = productStorage.getAllProducts();
        assertEquals(2, products.size());
    }
}
