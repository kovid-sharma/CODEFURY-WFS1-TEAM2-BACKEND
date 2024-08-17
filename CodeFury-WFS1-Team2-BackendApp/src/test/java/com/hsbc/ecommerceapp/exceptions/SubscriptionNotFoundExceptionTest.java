package com.hsbc.ecommerceapp.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubscriptionNotFoundExceptionTest {

    @Test
    void testExceptionWithMessage() {
        String message = "Subscription not found!";
        SubscriptionNotFoundException exception = new SubscriptionNotFoundException(message);

        assertEquals(message, exception.getMessage());
    }

    @Test
    void testExceptionWithMessageAndCause() {
        String message = "Subscription not found!";
        Throwable cause = new RuntimeException("Underlying cause");
        SubscriptionNotFoundException exception = new SubscriptionNotFoundException(message, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
