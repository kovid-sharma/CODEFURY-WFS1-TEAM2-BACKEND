package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.storage.UserStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceImplTest {
    private static UserStorage userStorage;
    private static UserServiceImpl userService;

    // initializing before all tests
    @BeforeAll
    public static void setup() {
        userStorage = new UserStorage();
        userService = new UserServiceImpl(userStorage);
    }

    // testing register new user
    @Test
    public void testRegisterUser() {
        User user = new User("User1", "john_wick", "password123", "john@wick.com");
        userService.registerUser(user);
        User fetchedUser = userStorage.getUserById("User1");
        assertNotNull(fetchedUser);
        assertEquals("john_wick", fetchedUser.getUserName());
    }

    // testing user login
    @Test
    public void testLoginUser() {
        User user = new User("User1", "john_wick", "password123", "john@wick.com");
        userService.registerUser(user);
        User loggedInUser = userService.loginUser("john_wick", "password123");
        assertNotNull(loggedInUser);
        assertEquals("john_wick", loggedInUser.getUserName());
    }

    // testing update user
    @Test
    public void testUpdateUser() {
        User user = new User("User1", "john_wick", "password123", "john@wick.com");
        userService.registerUser(user);
        user.setEmail("john.wick@new.com");
        userService.updateUser(user);
        User updatedUser = userStorage.getUserById("User1");
        assertEquals("john.wick@new.com", updatedUser.getEmail());
    }

    // testing delete user
    @Test
    public void testDeleteUser() {
        User user = new User("User1", "john_wick", "password123", "john@wick.com");
        userService.registerUser(user);
        userService.deleteUser("1");
        assertThrows(UserNotFoundException.class, () -> userStorage.getUserById("User1"));
    }

    // testing invalid login
    @Test
    public void testInvalidLogin() {
        assertThrows(UserNotFoundException.class, () -> userService.loginUser("non_existing_user", "wrong_password"));
    }

    // testing invalid user registration
    @Test
    public void testInvalidUserRegistration() {
        assertThrows(InvalidInputException.class, () -> userService.registerUser(null));
    }
}
