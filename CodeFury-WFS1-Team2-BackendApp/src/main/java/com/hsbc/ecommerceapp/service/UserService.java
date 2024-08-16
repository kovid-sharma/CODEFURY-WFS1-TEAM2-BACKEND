package com.hsbc.ecommerceapp.service;

import com.hsbc.ecommerceapp.model.User;

public interface UserService {
    void registerUser(User user);
    void updateUser(User user);
    void deleteUser(String userId);
    User loginUser(String username, String password);
}
