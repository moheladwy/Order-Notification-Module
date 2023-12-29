package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Helper.LoginRequest;
import org.fcai.OrderNotificationModule.Models.User;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
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
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return context.userRepository.isUserExist(username, password);
    }

    @PostMapping("/register/")
    public void register(@RequestBody User user) {
        context.userRepository.registerNewUser(user);
    }

}