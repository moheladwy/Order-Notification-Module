package org.fcai.OrderNotificationModule.Helpers;

import org.fcai.OrderNotificationModule.Enums.OrderStatus;
import org.fcai.OrderNotificationModule.Models.*;
import org.springframework.stereotype.Component;

@Component
public class NotificationFactory {
    public static Notification createNotification(NotificationSpecs specs, OrderStatus status) {
        switch (status) {
            case OrderPlaced -> {
                return new OrderPlacedNotification(specs);
            }
            case OrderShipped -> {
                return new OrderShippedNotification(specs);
            }
            case OrderCancelled -> {
                return new OrderCancelledNotification(specs);
            }
            default -> throw new IllegalArgumentException("Invalid order status");
        }
    }
}