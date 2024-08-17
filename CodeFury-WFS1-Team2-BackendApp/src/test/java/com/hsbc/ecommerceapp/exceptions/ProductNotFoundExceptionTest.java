package com.hsbc.ecommerceapp.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Product not found!";
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            throw new ProductNotFoundException(message);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionMessageAndCause() {
        String message = "Product not found!";
        Throwable cause = new RuntimeException("Underlying cause");
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> {
            throw new ProductNotFoundException(message, cause);
        });
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
