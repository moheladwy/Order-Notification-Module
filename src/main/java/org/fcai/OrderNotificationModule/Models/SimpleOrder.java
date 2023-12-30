package org.fcai.OrderNotificationModule.Models;

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
        // TODO: Implement logic to generate order details as a string
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(specs.getId()).append("\n");
        orderDetails.append("User: ").append(specs.getUser().getUsername()).append("\n");
        orderDetails.append("Creation Date: ").append(specs.getCreationDate()).append("\n");
        orderDetails.append("Products:\n");
        for (Map.Entry<Product, Integer> entry : specs.getProducts().entrySet()) {
            orderDetails.append(entry.getKey().getName()).append(": ").append(entry.getValue()).append(" units\n");
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
