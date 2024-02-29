package org.fcai.OrderNotificationModule.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product with id " + id + " does not exist.");
    }
}
