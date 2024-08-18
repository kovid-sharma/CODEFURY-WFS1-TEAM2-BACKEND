package com.hsbc.ecommerceapp.exceptions;

import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InvalidInputExceptionTest {

    @Test
    public void testInvalidInputExceptionMessage() {
        String errorMessage = "Invalid input provided";
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(errorMessage);
        });
        assertEquals(errorMessage, exception.getMessage());
        System.out.println("Invalid Input Exception Tested");
    }

    @Test
    public void testInvalidInputExceptionWithCause() {
        String errorMessage = "Invalid input caused by another exception";
        Throwable cause = new RuntimeException("Cause of the error");
        InvalidInputException exception = assertThrows(InvalidInputException.class, () -> {
            throw new InvalidInputException(errorMessage, cause);
        });
        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
        System.out.println("Invalid Input Exception with Cause Tested");
    }
}
