package org.fcai.ordernotificationmodule.Exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Product with id " + id + " does not exist.");
    }
}
