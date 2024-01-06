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
        try {
            this.orderNumber = orderNumber;
            setCustomerName(customerName);
            setRefundAmount(refundAmount);
            setNumberDays(nDays);
            this.channel = channel;
            this.language = language;
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to make notification specs: " + e.getMessage());
            throw e;
        }
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null)
            throw new NullPointerException("Customer name cannot be null");
        this.customerName = customerName;
    }

    public void setRefundAmount(double refundAmount) {
        if (refundAmount <= 0)
            throw new IllegalArgumentException("Refund amount must be positive number");
        this.refundAmount = refundAmount;
    }

    public void setNumberDays(int nDays) {
        if (nDays < 0)
            throw new IllegalArgumentException("Number of days cannot be negative");
        this.nDays = nDays;
    }

    public void setChannel(NotificationChannel channel) {
        if (channel == null)
            throw new NullPointerException("Channel cannot be null");
        this.channel = channel;
    }

    public void setLanguage(NotificationLanguage language) {
        if (language == null)
            throw new NullPointerException("Language cannot be null");
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
