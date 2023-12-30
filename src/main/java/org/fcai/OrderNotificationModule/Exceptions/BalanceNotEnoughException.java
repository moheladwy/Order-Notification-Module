package org.fcai.OrderNotificationModule.Exceptions;

public class BalanceNotEnoughException extends RuntimeException{
    public BalanceNotEnoughException(double balance, double price) {
        super("Not enough balance to buy product with price " + price + ". Current balance is " + balance + ".");
    }
}
