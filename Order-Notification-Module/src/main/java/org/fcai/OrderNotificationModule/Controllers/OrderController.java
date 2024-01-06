package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.DTOs.CompoundOrderDto;
import org.fcai.OrderNotificationModule.DTOs.OrderResponseDto;
import org.fcai.OrderNotificationModule.DTOs.SimpleOrderDto;
import org.fcai.OrderNotificationModule.DTOs.UpdateOrderStatusDto;
import org.fcai.OrderNotificationModule.Enums.NotificationChannel;
import org.fcai.OrderNotificationModule.Enums.NotificationLanguage;
import org.fcai.OrderNotificationModule.Enums.OrderStatus;
import org.fcai.OrderNotificationModule.Exceptions.OrderCancellationDurationException;
import org.fcai.OrderNotificationModule.Exceptions.OrderNotFoundException;
import org.fcai.OrderNotificationModule.Helpers.NotificationProcessor;
import org.fcai.OrderNotificationModule.Helpers.OrderProcessor;
import org.fcai.OrderNotificationModule.Helpers.PaymentProcessor;
import org.fcai.OrderNotificationModule.Models.Order;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final DbContext context;
    private final OrderProcessor orderProcessor;
    private final PaymentProcessor paymentProcessor;
    private final NotificationProcessor notificationProcessor;
    private final int MAX_CANCEL_DURATION_IN_MINUTES;

    @Autowired
    public OrderController(DbContext context , OrderProcessor orderProcessor, PaymentProcessor paymentProcessor, NotificationProcessor notificationProcessor) {
        this.context = context;
        this.orderProcessor = orderProcessor;
        this.paymentProcessor = paymentProcessor;
        this.notificationProcessor = notificationProcessor;
        MAX_CANCEL_DURATION_IN_MINUTES = 30;
    }

    // Done.
    @GetMapping("/get-all")
    public List<OrderResponseDto> getAllOrders() {
        try {
            List<Order> orders = context.orderRepository.getAll();
            List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
            for (Order order : orders) {
                var orderResponseDto = orderProcessor.getOrderResponseDto(order);
                orderResponseDtos.add(orderResponseDto);
            }
            return orderResponseDtos;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    // Done.
    @GetMapping("/get-by-id/{id}")
    public Order getOrderById(@PathVariable int id) {
        try {
            return context.orderRepository.getById(id);
        } catch (OrderNotFoundException e) {
            System.err.println("Order not found: " + e.getMessage());
            throw e;
        }
    }

    // Done.
    @GetMapping("/get-order-details/{id}")
    public String getOrderDetails(@PathVariable int id) {
        try {
            return context.orderRepository.getById(id).getOrderDetails();
        } catch (OrderNotFoundException e) {
            System.err.println("Order not found: " + e.getMessage());
            throw e;
        }
    }

    // Done.
    @PutMapping("/update-order-status")
    public void updateOrderStatus(@RequestBody UpdateOrderStatusDto orderStatusDto) {
        try {
            Order order = context.orderRepository.getById(orderStatusDto.orderId);
            order.setStatus(orderStatusDto.status);
            notificationProcessor.sendNotification(order, NotificationLanguage.English, NotificationChannel.Email);
        } catch (OrderNotFoundException e) {
            System.err.println("Order not found: " + e.getMessage());
            throw e;
        }
    }

    // Done.
    @PostMapping("/create-simple-order")
    public void CreateSimpleOrder(@RequestBody SimpleOrderDto simpleOrderDto) {
        try {
            Order order = orderProcessor.createSimpleOrder(simpleOrderDto);
            context.orderRepository.createSimpleOrder(order);
            paymentProcessor.pay(order.getId());
            notificationProcessor.sendNotification(order, NotificationLanguage.English, NotificationChannel.Email);
        } catch (NullPointerException e) {
            System.err.println("Order cannot be null: " + e.getMessage());
            throw e;
        }
    }

    // Done.
    @PostMapping("/create-compound-order")
    public void createCompoundOrder(@RequestBody CompoundOrderDto compoundOrderDto) {
        try {
            Order order = orderProcessor.createCompoundOrder(compoundOrderDto);
            context.orderRepository.createCompoundOrder(order);
            notificationProcessor.sendNotification(order, NotificationLanguage.English, NotificationChannel.Email);
        } catch (NullPointerException e) {
            System.err.println("Order cannot be null: " + e.getMessage());
            throw e;
        }
    }

    // Done.
    @DeleteMapping("/cancel-order/{id}")
    public void cancelOrder(@PathVariable int id) {
        try {
            Order order = context.orderRepository.getById(id);
            if (order.getStatus() == OrderStatus.OrderShipped) {
                long duration = Duration.between(order.getCreationDate(), LocalDateTime.now()).toMinutes();
                if (duration > MAX_CANCEL_DURATION_IN_MINUTES)
                    throw new OrderCancellationDurationException("Cannot delete order after " + MAX_CANCEL_DURATION_IN_MINUTES + " minutes of Shipment.");
            }
            order.setStatus(OrderStatus.OrderCancelled);
            notificationProcessor.sendNotification(order, NotificationLanguage.English, NotificationChannel.Email);
        } catch (OrderNotFoundException e) {
            System.err.println("Order not found: " + e.getMessage());
            throw e;
        } catch (OrderCancellationDurationException e) {
            System.err.println("Order cancellation duration exception: " + e.getMessage());
            throw e;
        }
    }
}