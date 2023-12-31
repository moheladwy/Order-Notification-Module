package org.fcai.OrderNotificationModule.Helpers;

import org.fcai.OrderNotificationModule.DTOs.CompoundOrderDto;
import org.fcai.OrderNotificationModule.DTOs.SimpleOrderDto;
import org.fcai.OrderNotificationModule.Enums.OrderStatus;
import org.fcai.OrderNotificationModule.Exceptions.BalanceNotEnoughException;
import org.fcai.OrderNotificationModule.Exceptions.UserNotFoundException;
import org.fcai.OrderNotificationModule.Models.*;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderProcessor {
    private final DbContext context;
    private final PaymentProcessor paymentProcessor;
    private final int DISCOUNT_SHIPPING_FEES_PERCENTAGE;
    @Autowired
    public OrderProcessor(DbContext context, PaymentProcessor paymentProcessor) {
        this.context = context;
        this.paymentProcessor = paymentProcessor;
        DISCOUNT_SHIPPING_FEES_PERCENTAGE = 10;
    }

    // Done.
    public double calculateShippingFees(double shippingFees, int discountPercentage) {
        return Math.max(0, shippingFees - (shippingFees * discountPercentage / 100));
    }

    // Done.
    public int generateNewOrderId() {
        return context.orderRepository.getNumberOfOrders() + 1;
    }

    // Done.
    public Order createSimpleOrder(SimpleOrderDto simpleOrderDto) {
        User user = context.userRepository.getUserByUsername(simpleOrderDto.username);
        if (user == null) throw new UserNotFoundException(simpleOrderDto.username);
        OrderSpecs orderSpecs = new OrderSpecs(
                generateNewOrderId(),
                user,
                calculateShippingFees(simpleOrderDto.shippingFees, 0),
                OrderStatus.OrderPlaced
        );
        for (List<Integer> productData : simpleOrderDto.products) {
            Product product = context.productRepository.getById(productData.get(0));
            int quantity = productData.get(1);
            orderSpecs.addProduct(product, quantity);
        }
        Order order = new SimpleOrder(orderSpecs);
        if (user.getBalance() < order.getTotalPrice()) {
            throw new BalanceNotEnoughException(user, order.getTotalPrice());
        }
        return order;
    }

    // Done.
    public Order createCompoundOrder(CompoundOrderDto compoundOrderDto) {
        List<Order> simpleOrders = new ArrayList<>();
        double shippingFees = calculateShippingFees(compoundOrderDto.shippingFees,
                DISCOUNT_SHIPPING_FEES_PERCENTAGE);
        OrderSpecs specsOfSuperUser = null;
        for (SimpleOrderDto simpleOrderDto : compoundOrderDto.simpleOrders) {
            Order order = createSimpleOrder(simpleOrderDto);
            order.setShippingFees(shippingFees);
            context.orderRepository.createSimpleOrder(order);
            paymentProcessor.pay(order.getId());
            simpleOrders.add(order);
            if (simpleOrderDto.username.equals(compoundOrderDto.usernameOfSuperUser)) {
                specsOfSuperUser = order.getSpecs();
            }
        }
        return new CompoundOrder(specsOfSuperUser, simpleOrders);
    }
}
