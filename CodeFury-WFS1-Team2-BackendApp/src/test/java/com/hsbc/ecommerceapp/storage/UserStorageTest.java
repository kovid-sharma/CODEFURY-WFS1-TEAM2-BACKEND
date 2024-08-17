package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStorageTest {

    private UserStorage userStorage;

    @BeforeEach
    public void setup() {
        userStorage = new UserStorage();
    }

    @Test
    public void testAddUser() {
        User user = new User("User1", "user1@example.com", "1234 Elm Street", "123-456-7890");
        userStorage.addUser(user);

        User fetchedUser = userStorage.getUserById("User1");
        assertNotNull(fetchedUser);
        assertEquals("user1@example.com", fetchedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = new User("User1", "user1@example.com", "1234 Elm Street", "123-456-7890");
        userStorage.addUser(user);
        user.setEmail("updated@example.com");
        userStorage.updateUser(user);

        User updatedUser = userStorage.getUserById("User1");
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = new User("User1", "user1@example.com", "1234 Elm Street", "123-456-7890");
        userStorage.addUser(user);
        userStorage.deleteUser("User1");

        User deletedUser = userStorage.getUserById("User1");
        assertNull(deletedUser);
    }

    @Test
    public void testGetUserByUserName() {
        User user = new User("User1", "user1@example.com", "1234 Elm Street", "123-456-7890");
        userStorage.addUser(user);

        User fetchedUser = userStorage.getUserByUserName("User1");
        assertNotNull(fetchedUser);
        assertEquals("user1@example.com", fetchedUser.getEmail());
    }
}
