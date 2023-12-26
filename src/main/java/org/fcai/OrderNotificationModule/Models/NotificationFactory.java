package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;
import org.fcai.OrderNotificationModule.Enums.OrderStatus;

public class NotificationFactory {
    public static Notification createNotification(NotificationLanguage language, OrderStatus status) {
        String message;
        switch (status) {
            case OrderPlaced -> {
                if (language == NotificationLanguage.English)
                    message = "Dear %s, your order %d has been placed";
                else message = "عزيزي %s، تم إنشاء طلبك رقم %d بنجاح";
                return new Notification(NotificationChannel.EMAIL, NotificationLanguage.Arabic, message);
            }
            case OrderShipped -> {
                if (language == NotificationLanguage.English)
                    message = "Dear %s, your order %d has been shipped and will be delivered within %d days";
                else message = "عزيزي %s، تم شحن طلبك رقم %d وسيتم توصيله خلال %d أيام";
                return new Notification(NotificationChannel.EMAIL, NotificationLanguage.Arabic, message);
            }
            case OrderCancelled -> {
                if (language == NotificationLanguage.English)
                    message = "Dear %s, your order %d has been cancelled and you will be refunded %f within %d days";
                else message = "عزيزي %s، تم إلغاء طلبك رقم %d وسيتم إرجاع المبلغ %f خلال %d أيام";
                return new Notification(NotificationChannel.EMAIL, NotificationLanguage.Arabic, message);
            }
            default -> throw new IllegalArgumentException("Invalid order status");
        }
    }
}
