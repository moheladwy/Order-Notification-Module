package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void registerNewUser(User user) {
        // Check for null values
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("User, username, and password cannot be null");
        }
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("username, and password cannot be empty");
        }
        if (isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (user.getBalance() < 0) {
            throw new IllegalArgumentException("balance cannot be negative");
        }
        users.add(user);
    }

    private boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
