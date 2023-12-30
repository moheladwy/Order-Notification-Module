package org.fcai.OrderNotificationModule;

import jakarta.ws.rs.ApplicationPath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ApplicationPath("/")
public class OrderModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderModuleApplication.class, args);
    }
}