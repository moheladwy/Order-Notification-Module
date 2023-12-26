package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public class Notification {
    private final NotificationChannel channel;
    private final NotificationLanguage language;
    private final String message;

    public Notification(NotificationChannel channel, NotificationLanguage language, String message) {
        this.channel = channel;
        this.message = message;
        this.language = language;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

    public NotificationLanguage getLanguage() {
        return language;
    }
}
