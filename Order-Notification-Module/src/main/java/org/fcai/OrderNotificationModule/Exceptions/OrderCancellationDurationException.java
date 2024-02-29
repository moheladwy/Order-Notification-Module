package org.fcai.OrderNotificationModule.Exceptions;

public class OrderCancellationDurationException extends RuntimeException {
    public OrderCancellationDurationException(String message) {
        super(message);
    }
}
