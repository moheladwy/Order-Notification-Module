package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Models.User;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final DbContext context;

    @Autowired
    public AuthenticationController(DbContext context) {
        this.context = context;
    }

    // DONE.
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        try {
            context.userRepository.registerNewUser(user);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to register new user" + e.getMessage());
            throw e;
        }
    }
}
