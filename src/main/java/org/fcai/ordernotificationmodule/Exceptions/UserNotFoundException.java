package org.fcai.ordernotificationmodule.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User with id " + id + " does not exist.");
    }
}
