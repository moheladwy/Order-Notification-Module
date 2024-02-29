package org.fcai.OrderNotificationModule.Exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(int id) {
        super("Order with id = " + id + " not found.");
    }
}
