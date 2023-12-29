package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Enums.CategoryName;
import org.fcai.OrderNotificationModule.Models.Category;
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
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.addNewCategory(new Category(1, CategoryName.FOOD, "Soft drinks, coffees, teas, beers, and ales"));
        categoryRepository.addNewCategory(new Category(2, CategoryName.FURNITURE, "Chairs, tables, beds, desks, dressers, and cupboards"));
        categoryRepository.addNewCategory(new Category(3, CategoryName.GROCERIES, "Bread, cheese, eggs, milk, and other dairy products"));
        categoryRepository.addNewCategory(new Category(4, CategoryName.HEALTH, "Medicines, vitamins, bandages, and other medical supplies"));
        categoryRepository.addNewCategory(new Category(5, CategoryName.HOME, "Cleaning supplies, cooking utensils, and various other household items"));
        categoryRepository.addNewCategory(new Category(6, CategoryName.KITCHEN, "Pots, pans, dishes, cutlery, and various other kitchen items"));
        categoryRepository.addNewCategory(new Category(7, CategoryName.OFFICE, "Pens, pencils, paper, binders, and other office supplies"));
        categoryRepository.addNewCategory(new Category(8, CategoryName.OUTDOORS, "Lawn mowers, shovels, rakes, and other outdoor items"));
        categoryRepository.addNewCategory(new Category(9, CategoryName.SPORTS, "Balls, bats, gloves, and various other sports items"));
        categoryRepository.addNewCategory(new Category(10, CategoryName.TOYS, "Dolls, board games, puzzles, and various other toys"));
        categoryRepository.addNewCategory(new Category(11, CategoryName.OTHER, "Various other items"));

        return categoryRepository;
    }

    private static OrderRepository initializeOrderRepository() {
        return new OrderRepository(); // TODO: Implement OrderRepository Initialization
    }
}
