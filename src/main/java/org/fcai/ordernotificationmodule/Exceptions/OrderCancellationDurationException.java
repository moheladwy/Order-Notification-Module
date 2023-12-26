package org.fcai.ordernotificationmodule.Exceptions;

public class OrderCancellationDurationException extends RuntimeException {
    public OrderCancellationDurationException(String message) {
        super(message);
    }
}
