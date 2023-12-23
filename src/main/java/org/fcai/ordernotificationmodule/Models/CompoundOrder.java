package org.fcai.ordernotificationmodule.Models;

import java.util.List;

public class CompoundOrder implements Order {
    private OrderSpecs specs;
    private List<SimpleOrder> simpleOrders;
    public final int MAX_ORDERS;

    public CompoundOrder(OrderSpecs specs, int MAX_ORDERS, List<SimpleOrder> simpleOrders) {
        setSpecs(specs);
        this.MAX_ORDERS = MAX_ORDERS;
        setSimpleOrders(simpleOrders);
    }

    @Override
    public double getTotalPrice() {
        return 0;
    }

    @Override
    public String getOrderDetails() {
        return null;
    }

    public OrderSpecs getSpecs() {
        return specs;
    }

    public void setSpecs(OrderSpecs specs) {
        if (specs == null)
            throw new NullPointerException("OrderSpecs cannot be null");
        this.specs = specs;
    }

    public List<SimpleOrder> getSimpleOrders() {
        return simpleOrders;
    }

    public void setSimpleOrders(List<SimpleOrder> simpleOrders) {
        if (simpleOrders == null)
            throw new NullPointerException("SimpleOrders cannot be null");
        if (simpleOrders.size() > MAX_ORDERS)
            throw new IllegalArgumentException("SimpleOrders cannot be more than " + MAX_ORDERS + " orders");
        this.simpleOrders = simpleOrders;
    }
}
