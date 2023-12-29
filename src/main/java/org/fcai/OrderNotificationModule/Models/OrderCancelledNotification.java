package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class OrderCancelledNotification extends Notification {

    private final NotificationSpecs specs;

    public OrderCancelledNotification(NotificationSpecs specs) {
        super(specs.getChannel(), specs.getLanguage());
        if (language == NotificationLanguage.English)
            this.setTemplate("Dear %s, your order %d has been cancelled and you will be refunded %.2f within %d days");
        else
            this.setTemplate("عزيزي %s، تم إلغاء طلبك رقم %d وسيتم إرجاع المبلغ %.2f خلال %d أيام");
        this.specs = specs;
    }

    @Override
    public String getNotificationMessage() {
        return String.format(template, specs.getCustomerName(), specs.getOrderNumber(), specs.getRefundAmount(),
                specs.getNumberDays());
    }

}
