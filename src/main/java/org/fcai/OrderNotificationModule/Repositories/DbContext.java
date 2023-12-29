package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbContext {
    public final UserRepository userRepository;
    public final ProductRepository productRepository;
    public final CategoryRepository categoryRepository;
    public final OrderRepository orderRepository;

    public DbContext() {
        this.userRepository = initializeUserRepository();
        this.productRepository = initializeProductRepository();
        this.categoryRepository = initializeCategoryRepository();
        this.orderRepository = initializeOrderRepository();
    }

    private static UserRepository initializeUserRepository() {
        return new UserRepository(); // TODO: Implement UserRepository Initialization
    }

    private static ProductRepository initializeProductRepository() {
        return new ProductRepository(); // TODO: Implement ProductRepository Initialization
    }

    private static CategoryRepository initializeCategoryRepository() {
        return new CategoryRepository(); // TODO: Implement CategoryRepository Initialization
    }

    public static OrderRepository initializeOrderRepository() {
            OrderRepository orderRepository = new OrderRepository();

            User user1 = new User("user1", "User One", "user1@example.com", "password1", "Address1", "123456789", "Area1", 1000.0);
            User user2 = new User("user2", "User Two", "user2@example.com", "password2", "Address2", "987654321", "Area2", 1500.0);

            Product product1 = new Product(0, "Jeans", "Category1", "Brand1", 200.0);
            Product product2 = new Product(1, "T-shirts", "Category1", "Brand1", 150.0);
            Product product3 = new Product(2, "Smartphones", "Category2", "Brand2", 1000.0);

            OrderSpecs specs1 = new OrderSpecs(1, user1, 20.0);
            OrderSpecs specs2 = new OrderSpecs(2, user2, 30.0);

            SimpleOrder simpleOrder1 = new SimpleOrder(specs1);
            simpleOrder1.addProduct(product1, 2);
            simpleOrder1.addProduct(product2, 3);

            SimpleOrder simpleOrder2 = new SimpleOrder(specs2);
            simpleOrder2.addProduct(product3, 1);

            CompoundOrder compoundOrder = new CompoundOrder(specs1, 2, List.of(simpleOrder1, simpleOrder2));

            orderRepository.createSimpleOrder(simpleOrder1);
            orderRepository.createSimpleOrder(simpleOrder2);
            orderRepository.createCompoundOrder(compoundOrder);

            return orderRepository;
        }

}
