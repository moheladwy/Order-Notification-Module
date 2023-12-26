package org.fcai.ordernotificationmodule.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.fcai.ordernotificationmodule.Repositories.DbContext;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final DbContext context;

    @Autowired
    public NotificationController(DbContext context) {
        this.context = context;
    }
}
