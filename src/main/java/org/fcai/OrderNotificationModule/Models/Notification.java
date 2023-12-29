package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;

public abstract class Notification {

    protected final NotificationChannel channel;
    protected final NotificationLanguage language;

    protected String template;

    public Notification(NotificationChannel channel, NotificationLanguage language) {
        this.channel = channel;
        this.language = language;
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public NotificationLanguage getLanguage() {
        return language;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public abstract String getNotificationMessage();
}
