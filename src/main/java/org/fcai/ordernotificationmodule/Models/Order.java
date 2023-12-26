package org.fcai.ordernotificationmodule.Models;

import java.time.LocalDateTime;

public interface Order {
    double getTotalPrice();
    String getOrderDetails();
    LocalDateTime getCreationDate();
}
