package org.fcai.OrderNotificationModule.Models;

import java.time.LocalDateTime;
import java.util.List;

public class CompoundOrder implements Order {
    private OrderSpecs specs;
    private List<Order> simpleOrders;
    public final int MAX_ORDERS;

    public CompoundOrder(OrderSpecs specs, int MAX_ORDERS, List<Order> simpleOrders) {
        try {
            setSpecs(specs);
            this.MAX_ORDERS = MAX_ORDERS;
            setSimpleOrders(simpleOrders);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to make Compound Order: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = specs.getProductsPrice() + specs.getShippingFees();
        for (Order order : simpleOrders) {
            totalPrice += order.getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public String getOrderDetails() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Compound Order ID: ").append(specs.getId()).append("\n");

        User user = specs.getUser();
        if (user != null) {
            orderDetails.append("User: ").append(user.getUsername()).append("\n");
        } else {
            orderDetails.append("User: Unknown\n");
        }

        orderDetails.append("Creation Date: ").append(specs.getCreationDate()).append("\n");
        orderDetails.append("Simple Orders:\n");

        if (simpleOrders != null) {
            for (Order simpleOrder : simpleOrders) {
                orderDetails.append(simpleOrder.getOrderDetails()).append("\n");
            }
        }

        orderDetails.append("Total Price: ").append(getTotalPrice()).append("\n");

        return orderDetails.toString();
    }

    @Override
    public LocalDateTime getCreationDate() {
        return specs.getCreationDate();
    }

    @Override
    public User getUser() {
        return specs.getUser();
    }

    @Override
    public int getId() {
        return specs.getId();
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
