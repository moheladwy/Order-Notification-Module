package org.fcai.OrderNotificationModule.DTOs;

import org.fcai.OrderNotificationModule.Enums.OrderStatus;
import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Models.User;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDto {
    public int id;
    public List<List<Integer>> productsQuantities;
    public List<Product> products;
    public double productsPrice;
    public double shippingFees;
    public User user;
    public LocalDateTime creationDate;
    public OrderStatus status;
}
