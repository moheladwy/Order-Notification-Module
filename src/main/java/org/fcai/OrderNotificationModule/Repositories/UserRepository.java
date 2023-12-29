package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public User isUserExist(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void registerNewUser(User user) {
        if (isUsernameTaken(user.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
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
