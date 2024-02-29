package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class OrderShippedNotification extends Notification {
    public OrderShippedNotification(NotificationSpecs specs) {
        super(specs);
        if (specs.getLanguage() == NotificationLanguage.English)
            this.setTemplate("Dear %s, your order %d has been shipped and will be delivered within %d days");
        else
            this.setTemplate("عزيزي %s، تم شحن طلبك رقم %d وسيتم توصيله خلال %d أيام");
    }

    @Override
    public String getNotificationMessage() {
        return String.format(template, specs.getCustomerName(), specs.getOrderNumber(), specs.getNumberDays());
    }
}
