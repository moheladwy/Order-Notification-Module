package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Exceptions.OrderNotFoundException;
import org.fcai.OrderNotificationModule.Models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders;

    public OrderRepository() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getAll() {
        return orders;
    }

    public int getNumberOfOrders() {
        int numberOfOrders = 0;
        for (Order order : orders) {
            numberOfOrders += order.getOrderCount();
        }
        return numberOfOrders;
    }

    public Order getById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        throw new OrderNotFoundException(id);
    }

    public void createSimpleOrder(Order order) {
        if (order == null) {
            throw new NullPointerException("Order cannot be null");
        }
        orders.add(order);
    }

    public void createCompoundOrder(Order order) {
        if (order == null) {
            throw new NullPointerException("Order cannot be null");
        }
        orders.add(order);
    }
}

