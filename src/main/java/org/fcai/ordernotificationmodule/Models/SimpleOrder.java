package org.fcai.ordernotificationmodule.Models;

import java.util.HashMap;

public class SimpleOrder implements Order {
    private OrderSpecs specs;

    public SimpleOrder(OrderSpecs specs) {
        if (specs == null)
            throw new NullPointerException("Order Specs cannot be null");
        this.specs = specs;
    }

    @Override
    public double getTotalPrice() {
        return 0; // TODO: to be implemented
    }

    @Override
    public String getOrderDetails() {
        return null; // TODO: to be implemented
    }

    public OrderSpecs getSpecs() {
        return specs;
    }

    public void setSpecs(OrderSpecs specs) {
        if (specs == null)
            throw new NullPointerException("Order Specs cannot be null");
        this.specs = specs;
    }

    public void addProduct(Product product, int quantity) {
        specs.addProduct(product, quantity);
    }

    public void removeProduct(Product product, int quantity) {
        specs.removeProduct(product, quantity);
    }
}
