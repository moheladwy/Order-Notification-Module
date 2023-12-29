package org.fcai.OrderNotificationModule.Models;

import java.time.LocalDateTime;

public interface Order {
    double getTotalPrice();
    String getOrderDetails();
    LocalDateTime getCreationDate();
    User getUser();
    int getId();
}
