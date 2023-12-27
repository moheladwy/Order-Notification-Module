package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final DbContext context;

    @Autowired
    public NotificationController(DbContext context) {
        this.context = context;
    }
}
