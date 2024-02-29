package org.fcai.OrderNotificationModule.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(int id) {
        super("Category with id " + id + " does not exist.");
    }
}
