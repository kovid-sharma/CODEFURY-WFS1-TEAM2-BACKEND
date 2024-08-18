package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStorageTest {

    private UserStorage userStorage;
    private User user = null;

    @BeforeEach
    public void setup() {
        userStorage = new UserStorage();
        user = new User("User1", "john_wick", "john@123", "user1@example.com", "customer");
    }

    @Test
    public void testAddUser() {
        userStorage.addUser(user);

        User fetchedUser = userStorage.getUserById("User1");
        assertNotNull(fetchedUser);
        assertEquals("user1@example.com", fetchedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        user.setEmail("updated@example.com");
        userStorage.updateUser(user);

        User updatedUser = userStorage.getUserById("User1");
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        userStorage.addUser(user);
        userStorage.deleteUser("User1");

        User deletedUser = userStorage.getUserById("User1");
        assertNull(deletedUser);
    }

    @Test
    public void testGetUserByUserName() {
        userStorage.addUser(user);

        User fetchedUser = userStorage.getUserById("User1");
        assertNotNull(fetchedUser);
        assertEquals("user1@example.com", fetchedUser.getEmail());
    }
}
