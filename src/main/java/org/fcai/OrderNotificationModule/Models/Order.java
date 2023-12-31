package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.OrderStatus;

import java.time.LocalDateTime;

public interface Order {
    double getTotalPrice();
    String getOrderDetails();
    LocalDateTime getCreationDate();
    int getId();
    User getUser();
    OrderStatus getStatus();
    void setStatus(OrderStatus status);
    void setShippingFees(double shippingFees);
    double getShippingFees();
    int getOrderCount();
    OrderSpecs getSpecs();
}
