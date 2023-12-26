package org.fcai.OrderNotificationModule.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User with id " + id + " does not exist.");
    }
}
