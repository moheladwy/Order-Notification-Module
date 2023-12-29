package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try {
            System.out.println("Payment processed for order ID: " + orderId);
            return true;
        } catch (Exception e) {
            System.err.println("Payment failed for order ID: " + orderId);
            e.printStackTrace();
            return false;
        }
    }
}
