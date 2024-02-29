package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public class CompoundOrder implements Order {
    private OrderSpecs specs;
    private List<Order> simpleOrders;

    public CompoundOrder(OrderSpecs specs, List<Order> simpleOrders) {
        try {
            setSpecs(specs);
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
    public OrderStatus getStatus() {
        return specs.getStatus();
    }

    @Override
    public void setStatus(OrderStatus status) {
        if (status == null)
            throw new NullPointerException("Order Status cannot be null");
        specs.setStatus(status);
        for (Order order : simpleOrders) {
            order.setStatus(status);
        }
    }

    @Override
    public void setShippingFees(double shippingFees) {
        specs.setShippingFees(shippingFees);
    }

    @Override
    public double getShippingFees() {
        return specs.getShippingFees();
    }

    @Override
    public int getOrderCount() {
        int orderCount = 1;
        for (Order order : simpleOrders) {
            orderCount += order.getOrderCount();
        }
        return orderCount;
    }

    @Override
    public int getId() {
        return specs.getId();
    }

    @Override
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
        this.simpleOrders = simpleOrders;
    }
}
