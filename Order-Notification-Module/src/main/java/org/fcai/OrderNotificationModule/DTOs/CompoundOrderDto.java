package org.fcai.OrderNotificationModule.DTOs;

import java.util.List;

public class CompoundOrderDto {
    public List<SimpleOrderDto> simpleOrders;
    public double shippingFees;
    public String usernameOfSuperUser;
}
