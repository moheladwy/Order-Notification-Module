package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final DbContext context;

    @Autowired
    public PaymentController(DbContext context) {
        this.context = context;
    }

    @GetMapping("/pay-order/{orderId}")
    public boolean pay(@PathVariable int orderId) {
        return true; // TODO: Implement Payment Logic
    }
}
