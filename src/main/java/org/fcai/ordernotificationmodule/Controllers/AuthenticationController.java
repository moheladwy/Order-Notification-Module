package org.fcai.ordernotificationmodule.Controllers;

import org.fcai.ordernotificationmodule.Models.User;
import org.fcai.ordernotificationmodule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final DbContext context;

    @Autowired
    public AuthenticationController(DbContext context) {
        this.context = context;
    }

    @PostMapping("/authenticate/")
    public User authenticate(@RequestBody String username, @RequestBody String password) {
        return context.userRepository.isUserExist(username, password);
    }

    @PostMapping("/register/")
    public void register(@RequestBody User user) {
        context.userRepository.registerNewUser(user);
    }
}
