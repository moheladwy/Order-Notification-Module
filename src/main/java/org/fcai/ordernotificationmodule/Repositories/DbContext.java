package org.fcai.ordernotificationmodule.Repositories;

import org.springframework.stereotype.Component;

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

    private static OrderRepository initializeOrderRepository() {
        return new OrderRepository(); // TODO: Implement OrderRepository Initialization
    }
}
