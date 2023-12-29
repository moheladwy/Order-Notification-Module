package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class OrderPlacedNotification extends Notification {
    private final NotificationSpecs specs;

    public OrderPlacedNotification(NotificationSpecs specs) {
        super(specs.getChannel(), specs.getLanguage());
        if (language == NotificationLanguage.English)
            this.setTemplate("Dear %s, your order %d has been placed successfully");
        else
            this.setTemplate("عزيزي %s، تم إنشاء طلبك رقم %d بنجاح");
        this.specs = specs;
    }

    @Override
    public String getNotificationMessage() {
        return String.format(template, specs.getCustomerName(), specs.getOrderNumber());
    }
}
