package com.hsbc.ecommerceapp.storage;

import com.hsbc.ecommerceapp.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> userMap = new HashMap<>();

    public void addUser(User user) {
        userMap.put(user.getUserName(), user);
    }

    public void updateUser(User user) {
        userMap.put(user.getUserName(), user);
    }

    public void deleteUser(String userId) {
        userMap.remove(userId);
    }

    public User getUserById(String userId) {
        return userMap.get(userId);
    }

    public User getUserByUserName(String userName) {
        for(User user : userMap.values())
            if(user.getUserName().equals(userName))
                return user;
        return null;
    }
}
