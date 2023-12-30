package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class OrderPlacedNotification extends Notification {

    public OrderPlacedNotification(NotificationSpecs specs) {
        super(specs);
        if (specs.getLanguage() == NotificationLanguage.English)
            this.setTemplate("Dear %s, your order %d has been placed successfully");
        else
            this.setTemplate("عزيزي %s، تم إنشاء طلبك رقم %d بنجاح");
    }

    @Override
    public String getNotificationMessage() {
        return String.format(template, specs.getCustomerName(), specs.getOrderNumber());
    }
}
