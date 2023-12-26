package org.fcai.ordernotificationmodule.Controllers;

import org.fcai.ordernotificationmodule.Exceptions.OrderCancellationDurationException;
import org.fcai.ordernotificationmodule.Exceptions.OrderNotFoundException;
import org.fcai.ordernotificationmodule.Models.Order;
import org.fcai.ordernotificationmodule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/Orders")
public class OrderController {
    private final DbContext context;

    @Autowired
    public OrderController(DbContext context) {
        this.context = context;
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
        if (duration > 30)
            throw new OrderCancellationDurationException("Cannot delete order after 30 minutes of creation");
        context.orderRepository.delete(order);
    }
}
