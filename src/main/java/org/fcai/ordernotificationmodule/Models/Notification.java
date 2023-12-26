package org.fcai.ordernotificationmodule.Models;

public class Notification {
    private final NotificationChannel channel;
    private final String message;

    public Notification(NotificationChannel channel, String message) {
        this.channel = channel;
        this.message = message;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }
}
