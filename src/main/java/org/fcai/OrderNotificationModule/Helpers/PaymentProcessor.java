package org.fcai.OrderNotificationModule.Helpers;

import org.fcai.OrderNotificationModule.Exceptions.BalanceNotEnoughException;
import org.fcai.OrderNotificationModule.Exceptions.OrderNotFoundException;
import org.fcai.OrderNotificationModule.Models.Order;
import org.fcai.OrderNotificationModule.Models.User;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {
    private final DbContext context;

    @Autowired
    public PaymentProcessor(DbContext context) {
        this.context = context;
    }

    public void pay(int orderId) throws OrderNotFoundException, BalanceNotEnoughException {
        Order order = context.orderRepository.getById(orderId);

        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }

        double orderTotalPrice = order.getTotalPrice();
        User user = order.getUser();
        if (orderTotalPrice > user.getBalance()) {
            throw new BalanceNotEnoughException(user, orderTotalPrice);
        }
        user.setBalance(user.getBalance() - orderTotalPrice);
    }
}
