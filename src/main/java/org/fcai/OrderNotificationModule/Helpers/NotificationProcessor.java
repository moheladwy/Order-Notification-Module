package org.fcai.OrderNotificationModule.Helpers;

import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;
import org.fcai.OrderNotificationModule.Helpers.NotificationFactory;
import org.fcai.OrderNotificationModule.Models.Notification;
import org.fcai.OrderNotificationModule.Models.NotificationSpecs;
import org.fcai.OrderNotificationModule.Models.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

// Service that sends notifications to the user.
@EnableScheduling
@Component
public class NotificationProcessor {
    private final Queue<Notification> notificationQueue;

    public NotificationProcessor() {
        this.notificationQueue = new LinkedList<>();
    }

    public void sendNotification(Order order,
                                 NotificationLanguage language,
                                 NotificationChannel channel) {
        try {
            NotificationSpecs specs = new NotificationSpecs(order.getId(),
                    order.getUser().getName(), order.getTotalPrice(), 7, channel, language);
            Notification notification = NotificationFactory.createNotification(specs, order.getStatus());
            notificationQueue.add(notification);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Cannot send notification: " + e.getMessage());
            throw e;
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void sendNotification() throws IOException {
        try {
            File file = new File("target/Logging/SendingNotificationLoggingFile.txt");
            FileOutputStream outputStream = new FileOutputStream(file, true);
            String notificationLoggerHeader = "";
            if (notificationQueue.isEmpty()) {
                notificationLoggerHeader = "No notifications to send\n";
                System.out.printf(notificationLoggerHeader);
                outputStream.write(notificationLoggerHeader.getBytes());
            } else {
                notificationLoggerHeader = notificationQueue.poll().toString();
                notificationLoggerHeader = notificationLoggerHeader
                        .concat(String.format(", Sent at: %s\n", LocalDateTime.now().toString()));
                System.out.printf(notificationLoggerHeader);
                outputStream.write(notificationLoggerHeader.getBytes());
            }
            outputStream.close();
        }
        catch (IOException e) {
            System.err.println("Cannot send notification: " + e.getMessage());
            throw e;
        }
    }
}
