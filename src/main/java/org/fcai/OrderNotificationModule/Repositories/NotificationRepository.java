package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.Notification;

import java.util.HashMap;
import java.util.Map;

public class NotificationRepository {
    private final Map<String, Integer> notificationCount;
    private final Map<String, Integer> emailCount;
    private final Map<String, Integer> smsCount;

    public NotificationRepository() {
        this.notificationCount = new HashMap<>();
        this.emailCount = new HashMap<>();
        this.smsCount = new HashMap<>();
    }

    private void addToNotificationCountMap(String notificationName) {
        if (notificationCount.containsKey(notificationName)) {
            notificationCount.put(notificationName, notificationCount.get(notificationName) + 1);
        } else {
            notificationCount.put(notificationName, 1);
        }
    }

    private void addToEmailCountMap(String notificationName) {
        if (emailCount.containsKey(notificationName)) {
            emailCount.put(notificationName, emailCount.get(notificationName) + 1);
        } else {
            emailCount.put(notificationName, 1);
        }
    }

    private void addToSmsCountMap(String notificationName) {
        if (smsCount.containsKey(notificationName)) {
            smsCount.put(notificationName, smsCount.get(notificationName) + 1);
        } else {
            smsCount.put(notificationName, 1);
        }
    }

    public void addNotification(Notification notification) {
        if (notification == null) {
            throw new NullPointerException("Notification cannot be null");
        }
        String notificationName = notification.getClass().getSimpleName();
        addToNotificationCountMap(notificationName);
        switch (notification.getSpecs().getChannel()) {
            case Email:
                addToEmailCountMap(notificationName);
                break;
            case SMS:
                addToSmsCountMap(notificationName);
                break;
        }
    }

    public String getMostSentNotificationTemplate() {
        if (notificationCount.isEmpty()) {
            return "No notifications sent";
        }
        return getMostOccurs(notificationCount);
    }

    public String getMostNotifiedEmail() {
        if (emailCount.isEmpty()) {
            return "No emails sent";
        }
        return getMostOccurs(emailCount);
    }

    public String getMostNotifiedPhoneNumber() {
        if (smsCount.isEmpty()) {
            return "No SMS sent";
        }
        return getMostOccurs(smsCount);
    }

    private String getMostOccurs(Map<String, Integer> counter) {
        String most = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() > maxCount) {
                most = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return most;
    }
}
