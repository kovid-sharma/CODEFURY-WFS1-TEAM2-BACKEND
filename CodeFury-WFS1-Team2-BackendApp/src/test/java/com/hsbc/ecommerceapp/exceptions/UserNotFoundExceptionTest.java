package com.hsbc.ecommerceapp.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "User not found!";
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            throw new UserNotFoundException(message);
        });
        assertEquals(message, exception.getMessage());
        System.out.println("User Not Found Exception Tested");
    }

    @Test
    public void testExceptionMessageAndCause() {
        String message = "User not found!";
        Throwable cause = new Throwable("Cause of the exception");
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            throw new UserNotFoundException(message, cause);
        });
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
        System.out.println("User Not Found Exception with Cause Tested");
    }
}
