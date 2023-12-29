package org.fcai.OrderNotificationModule.Models;

import java.time.LocalDateTime;
import java.util.List;

public class CompoundOrder implements Order {
    private OrderSpecs specs;
    private List<Order> simpleOrders;
    public final int MAX_ORDERS;

    public CompoundOrder(OrderSpecs specs, int MAX_ORDERS, List<Order> simpleOrders) {
        setSpecs(specs);
        this.MAX_ORDERS = MAX_ORDERS;
        setSimpleOrders(simpleOrders);
    }

    @Override
    public double getTotalPrice() {
        return 0; // TODO: to be implemented
    }

    @Override
    public String getOrderDetails() {
        return null; // TODO: to be implemented
    }

    @Override
    public LocalDateTime getCreationDate() {
        return specs.getCreationDate();
    }

    @Override
    public int getId() {
        return specs.getId();
    }

    @Override
    public User getUser() {
        return specs.getUser();
    }

    public OrderSpecs getSpecs() {
        return specs;
    }

    public void setSpecs(OrderSpecs specs) {
        if (specs == null)
            throw new NullPointerException("OrderSpecs cannot be null");
        this.specs = specs;
    }

    public List<Order> getSimpleOrders() {
        return simpleOrders;
    }

    public void setSimpleOrders(List<Order> simpleOrders) {
        if (simpleOrders == null)
            throw new NullPointerException("SimpleOrders cannot be null");
        if (simpleOrders.size() > MAX_ORDERS)
            throw new IllegalArgumentException("SimpleOrders cannot be more than " + MAX_ORDERS + " orders");
        this.simpleOrders = simpleOrders;
    }
}
