package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.*;
import org.fcai.OrderNotificationModule.Enums.CategoryName;
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
        ProductRepository productRepository= new ProductRepository();
        productRepository.add(new Product(0,"Jeans","","Omar",200),60 );
        productRepository.add(new Product(1,"T-shirts","","Omar",200),70 );
        productRepository.add(new Product(2,"Jackets","","Omar",200),50 );
        productRepository.add(new Product(3,"Smartphones","","Amr",5000),10 );
        productRepository.add(new Product(4,"Laptops","","Amr",15000),100 );
        productRepository.add(new Product(5,"Cameras","","Amr",5000),60 );
        productRepository.add(new Product(6,"Fruits","","Mohamed",10),88 );
        productRepository.add(new Product(7,"Meat","","Mohamed",200),66 );
        productRepository.add(new Product(8,"Snacks","","Mohamed",10),44 );
        productRepository.add(new Product(9,"Sofas","","Adham",1000),12 );
        productRepository.add(new Product(10,"Beds","","Adham",2000),45 );
        productRepository.add(new Product(11,"Chairs","","Adham",1000),34 );
        productRepository.add(new Product(12,"Vitamins and supplements","","Adham",500),88 );
        productRepository.add(new Product(13,"Sports accessories","","Adham",500),98 );
        productRepository.add(new Product(14,"Dolls","","Omar",200),33 );
        productRepository.add(new Product(15,"Printers","","Omar",5000),33 );

        return productRepository;

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
