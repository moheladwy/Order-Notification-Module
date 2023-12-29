package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class NotificationSpecs {
    private int orderNumber;
    private String customerName;
    private double refundAmount;
    private int nDays;
    private NotificationChannel channel;
    private NotificationLanguage language;

    public NotificationSpecs(int orderNumber, String customerName, double refundAmount, int nDays,
                             NotificationChannel channel, NotificationLanguage language) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.refundAmount = refundAmount;
        this.nDays = nDays;
        this.channel = channel;
        this.language = language;
    }

    // make setters for all fields
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public void setNumberDays(int nDays) {
        this.nDays = nDays;
    }

    public void setChannel(NotificationChannel channel) {
        this.channel = channel;
    }

    public void setLanguage(NotificationLanguage language) {
        this.language = language;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public int getNumberDays() {
        return nDays;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public NotificationLanguage getLanguage() {
        return language;
    }
}
