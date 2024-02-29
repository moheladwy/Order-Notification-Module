package org.fcai.OrderNotificationModule.DTOs;

import org.fcai.OrderNotificationModule.Enums.OrderStatus;

public class UpdateOrderStatusDto {
    public int orderId;
    public OrderStatus status;
}
