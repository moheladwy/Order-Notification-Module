package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Exceptions.BalanceNotEnoughException;
import org.fcai.OrderNotificationModule.Exceptions.OrderNotFoundException;
import org.fcai.OrderNotificationModule.Exceptions.OrderCancellationDurationException;
import org.fcai.OrderNotificationModule.Models.Order;
import org.fcai.OrderNotificationModule.Models.User;
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
    public boolean pay(@PathVariable int orderId) throws OrderNotFoundException, OrderCancellationDurationException {
        Order order = context.orderRepository.getById(orderId);


        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        double orderTotalPrice = order.getTotalPrice();
        User user = order.getUser();
        if (orderTotalPrice > user.getBalance()) {
            throw new BalanceNotEnoughException( user.getBalance(),orderTotalPrice);
        }
        user.setBalance(user.getBalance() - orderTotalPrice);

        return true;
    }
}
