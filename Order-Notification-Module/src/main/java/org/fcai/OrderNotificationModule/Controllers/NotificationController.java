package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Helpers.NotificationProcessor;
import org.fcai.OrderNotificationModule.Models.Notification;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final DbContext dbContext;
    private final NotificationProcessor notificationProcessor;

    @Autowired
    public NotificationController(DbContext dbContext, NotificationProcessor notificationProcessor) {
        this.dbContext = dbContext;
        this.notificationProcessor = notificationProcessor;
    }

    @GetMapping("/get-notification-queue")
    public List<Notification> getNotificationQueueContent() {
        return List.copyOf(notificationProcessor.getNotificationQueue());
    }

    @GetMapping("/get-most-sent-template")
    public String getMostSentTemplate() {
        return dbContext.notificationRepository.getMostSentNotificationTemplate();
    }

    @GetMapping("/get-most-notified-email")
    public String getMostNotifiedEmail() {
        return dbContext.notificationRepository.getMostNotifiedEmail();
    }

    @GetMapping("/get-most-notified-phone-number")
    public String getMostNotifiedPhoneNumber() {
        return dbContext.notificationRepository.getMostNotifiedPhoneNumber();
    }
}
