package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class SimpleOrder implements Order {
    private OrderSpecs specs;

    public SimpleOrder(OrderSpecs specs) {
        if (specs == null)
            throw new NullPointerException("Order Specs cannot be null");
        this.specs = specs;
    }

    @Override
    public double getTotalPrice() {
        return specs.getProductsPrice() + specs.getShippingFees();
    }

    @Override
    public String getOrderDetails() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(specs.getId()).append("\n");
        orderDetails.append("User: ").append(specs.getUser().getUsername()).append("\n");
        orderDetails.append("Creation Date: ").append(specs.getCreationDate()).append("\n");
        orderDetails.append("Products:\n");
        for (Map.Entry<Product, Integer> entry : specs.getProducts().entrySet()) {
            orderDetails.append(entry.getKey().getName()).append(": ")
                    .append(entry.getValue()).append(" units, ")
                    .append(entry.getKey().getPrice()).append("$ for each unit.\n");
        }
        orderDetails.append("Shipping Fees: ").append(specs.getShippingFees()).append("$\n");
        orderDetails.append("Total Price: ").append(getTotalPrice()).append("$\n");

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
        return 1;
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
            throw new NullPointerException("Order Specs cannot be null");
        this.specs = specs;
    }

    public void addProduct(Product product, int quantity) {
        try {
            specs.addProduct(product, quantity);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Failed to add product: " + e.getMessage());
            throw e;
        }
    }

    public void removeProduct(Product product, int quantity) {
        try {
            specs.removeProduct(product, quantity);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Failed to remove product: " + e.getMessage());
            throw e;
        }
    }
}
