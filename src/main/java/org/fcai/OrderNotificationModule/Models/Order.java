package org.fcai.OrderNotificationModule.Models;

import java.time.LocalDateTime;

public interface Order {
    double getTotalPrice();
    String getOrderDetails();
    LocalDateTime getCreationDate();
    int getId();
    User getUser();
}
