package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Helper.LoginRequest;
import org.fcai.OrderNotificationModule.Models.User;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.fcai.OrderNotificationModule.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private final DbContext context;

    @Autowired
    public AuthenticationController(DbContext context) {
        this.context = context;
    }

    @PostMapping("/login/")
    public User login(@RequestBody LoginRequest loginRequest) {

        if(loginRequest == null) {
            throw new IllegalArgumentException("Login request cannot be null");
        }

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Check for null values
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        User user = context.userRepository.isUserExist(username, password);

        // Throw exception if user is not found
        if (user == null) {
            throw new UserNotFoundException(username);
        }

        return user;
    }

    @PostMapping("/register/")
    public void register(@RequestBody User user) {
        // Check for null values
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("username, and password cannot be null");
        }

        context.userRepository.registerNewUser(user);
    }

}
