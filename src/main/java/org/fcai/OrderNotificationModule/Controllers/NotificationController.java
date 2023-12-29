package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;
import org.fcai.OrderNotificationModule.Enums.OrderStatus;
import org.fcai.OrderNotificationModule.Models.*;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final DbContext context;
    private final Queue<Notification> notificationQueue;

    @Autowired
    public NotificationController(DbContext context) {
        this.context = context;
        this.notificationQueue = new LinkedList<>();
    }

    @PostMapping("/send-notification/{order-status}")
    public void sendNotification(@RequestBody Order order,
                                 @RequestBody NotificationLanguage language,
                                 @RequestBody NotificationChannel channel,
                                 @RequestBody OrderStatus status) {
        NotificationSpecs specs = new NotificationSpecs(order.getId(),
                order.getUser().getName(), order.getTotalPrice(), 7, channel, language);
        Notification notification = NotificationFactory.createNotification(specs, status);
        notificationQueue.add(notification);
    }
}
