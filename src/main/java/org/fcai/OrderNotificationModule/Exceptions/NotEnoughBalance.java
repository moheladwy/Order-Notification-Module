package org.fcai.OrderNotificationModule.Exceptions;

public class NotEnoughBalance extends RuntimeException{
    public NotEnoughBalance(double balance, double price) {
        super("Not enough balance to buy product with price " + price + ". Current balance is " + balance + ".");
    }
}
