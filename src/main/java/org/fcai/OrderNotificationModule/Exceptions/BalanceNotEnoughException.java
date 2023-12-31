package org.fcai.OrderNotificationModule.Exceptions;

import org.fcai.OrderNotificationModule.Models.User;

public class BalanceNotEnoughException extends RuntimeException{
    public BalanceNotEnoughException(User user, double price) {
        super("Not enough balance for user: " + user.getUsername() + " to buy product with price " + price + ". Current balance is " + user.getBalance() + ".");
    }
}
