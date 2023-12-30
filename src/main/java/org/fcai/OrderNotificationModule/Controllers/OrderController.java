package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Models.Order;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.fcai.OrderNotificationModule.Exceptions.OrderCancellationDurationException;
import org.fcai.OrderNotificationModule.Exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final DbContext context;
    private final int MAX_CANCEL_DURATION_IN_MINUTES;

    @Autowired
    public OrderController(DbContext context) {
        this.context = context;
        MAX_CANCEL_DURATION_IN_MINUTES = 30;
    }

    @GetMapping("/get-all")
    public List<Order> getAllOrders() {
        return context.orderRepository.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Order getOrderById(@PathVariable int id) {
        return context.orderRepository.getById(id);
    }

    @GetMapping("/get-order-details/{id}")
    public String getOrderDetails(@PathVariable int id) {
        Order order = context.orderRepository.getById(id);
        if (order == null)
            throw new OrderNotFoundException(id);
        return order.getOrderDetails();
    }

    @PostMapping("/create-simple-order")
    public void CreateSimpleOrder(@RequestBody Order order) {
        context.orderRepository.createSimpleOrder(order);
    }

    @PostMapping("/create-compound-order")
    public void createCompoundOrder(@RequestBody Order order) {
        context.orderRepository.createCompoundOrder(order);
    }

    @DeleteMapping("/cancel-order/{id}")
    public void cancelOrder(@PathVariable int id) {
        Order order = context.orderRepository.getById(id);
        if (order == null)
            throw new OrderNotFoundException(id);
        long duration = Duration.between(order.getCreationDate(), LocalDateTime.now()).toMinutes();
        if (duration > MAX_CANCEL_DURATION_IN_MINUTES)
            throw new OrderCancellationDurationException("Cannot delete order after " + MAX_CANCEL_DURATION_IN_MINUTES + " minutes of creation");
        context.orderRepository.delete(order);
    }
}