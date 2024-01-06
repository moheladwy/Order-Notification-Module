package org.fcai.OrderNotificationModule.Models;

public abstract class Notification {

    protected final NotificationSpecs specs;
    protected String template;

    public Notification(NotificationSpecs specs) {
        if (specs == null) {
            throw new NullPointerException("NotificationSpecs cannot be null");
        }
        this.specs = specs;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return String.format("Notification type: %s, " +
                            "Notification channel: %s, " +
                            "Notification language: %s, " +
                            "Notification message: %s.",
                            this.getClass().getSimpleName(),
                            this.specs.getChannel(), this.specs.getLanguage(),
                            this.getNotificationMessage());
    }

    public abstract String getNotificationMessage();

    public NotificationSpecs getSpecs() {
        return specs;
    }
}
