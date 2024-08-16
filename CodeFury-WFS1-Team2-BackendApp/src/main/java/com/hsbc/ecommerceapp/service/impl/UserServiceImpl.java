package com.hsbc.ecommerceapp.service.impl;

import com.hsbc.ecommerceapp.exceptions.InvalidInputException;
import com.hsbc.ecommerceapp.exceptions.UserNotFoundException;
import com.hsbc.ecommerceapp.model.User;
import com.hsbc.ecommerceapp.service.UserService;
import com.hsbc.ecommerceapp.storage.UserStorage;

public class UserServiceImpl implements UserService {
    private UserStorage userStorage;

    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public void registerUser(User user) {
        if (user == null || user.getUserName() == null || user.getPassword() == null)
            throw new InvalidInputException("Invalid user data");
        userStorage.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        if (userStorage.getUserById(user.getUserId()) == null)
            throw new UserNotFoundException("User not found!");
        userStorage.updateUser(user);
    }

    @Override
    public void deleteUser(String userId) {
        if (userStorage.getUserById(userId) == null)
            throw new UserNotFoundException("User not found!");
        userStorage.deleteUser(userId);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userStorage.getUserByUserName(username);
        if (user == null || !user.getPassword().equals(password))
            throw new UserNotFoundException("Invalid username or password");
        return user;
    }
}
