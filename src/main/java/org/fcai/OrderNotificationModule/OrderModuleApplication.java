package org.fcai.OrderNotificationModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderModuleApplication.class, args);
    }
}