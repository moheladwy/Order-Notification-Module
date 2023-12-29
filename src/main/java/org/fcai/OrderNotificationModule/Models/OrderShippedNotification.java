package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class OrderShippedNotification extends Notification {
    private final NotificationSpecs specs;

    public OrderShippedNotification(NotificationSpecs specs) {
        super(specs.getChannel(), specs.getLanguage());
        if (language == NotificationLanguage.English)
            this.setTemplate("Dear %s, your order %d has been shipped and will be delivered within %d days");
        else
            this.setTemplate("عزيزي %s، تم شحن طلبك رقم %d وسيتم توصيله خلال %d أيام");
        this.specs = specs;
    }

    @Override
    public String getNotificationMessage() {
        return String.format(template, specs.getCustomerName(), specs.getOrderNumber(), specs.getNumberDays());
    }
}
