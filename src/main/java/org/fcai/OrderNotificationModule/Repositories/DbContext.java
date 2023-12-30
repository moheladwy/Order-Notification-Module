package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Enums.CategoryName;
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
        try {
            userRepository = initializeUserRepository();
            productRepository = initializeProductRepository();
            categoryRepository = initializeCategoryRepository();
            orderRepository = initializeOrderRepository();
        } catch (Exception e) {
            System.err.println("Error initializing repositories: " + e.getMessage());
            throw e;
        }
    }

    private static UserRepository initializeUserRepository() {
        UserRepository userRepository = new UserRepository();

        userRepository.registerNewUser(new User("user1", "User One", "user1@example.com", "password1", "Address1", "123456789", "Area1", 2000.0));
        userRepository.registerNewUser(new User("user2", "User Two", "user2@example.com", "password2", "Address2", "987654321", "Area2", 1500.0));
        userRepository.registerNewUser(new User("user3", "User Three", "user3@example.com", "password3", "Address3", "123456789", "Area3", 2000.0));
        userRepository.registerNewUser(new User("user4", "User Four", "user4@example.com", "password4", "Address4", "987654321", "Area4", 2500.0));

        return userRepository;
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

    // TODO: Add products for each category.
    // ASSIGN: Adham
    private CategoryRepository initializeCategoryRepository() {
        CategoryRepository categoryRepository = new CategoryRepository();

        categoryRepository.addNewCategory(new Category(1, CategoryName.FOOD, "Soft drinks, coffees, teas, beers, and ales"));
        categoryRepository.addProductToCategory(1, productRepository.getById(0));
        categoryRepository.addProductToCategory(1, productRepository.getById(1));
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

    private OrderRepository initializeOrderRepository() {
        OrderRepository orderRepository = new OrderRepository();
        OrderSpecs specs1 = new OrderSpecs(1, userRepository.getUserByUsername("user1"), 20.0);
        OrderSpecs specs2 = new OrderSpecs(2, userRepository.getUserByUsername("user2"), 30.0);

        SimpleOrder simpleOrder1 = new SimpleOrder(specs1);
        simpleOrder1.addProduct(productRepository.getById(1), 2);
        simpleOrder1.addProduct(productRepository.getById(2), 3);

        SimpleOrder simpleOrder2 = new SimpleOrder(specs2);
        simpleOrder2.addProduct(productRepository.getById(3), 1);

        orderRepository.createSimpleOrder(simpleOrder1);
        orderRepository.createSimpleOrder(simpleOrder2);


        SimpleOrder simpleOrder3 = new SimpleOrder(new OrderSpecs(3, userRepository.getUserByUsername("user3"), 40.0));
        SimpleOrder simpleOrder4 = new SimpleOrder(new OrderSpecs(4, userRepository.getUserByUsername("user4"), 50.0));

        simpleOrder3.addProduct(productRepository.getById(4), 2);
        simpleOrder3.addProduct(productRepository.getById(5), 3);
        
        simpleOrder4.addProduct(productRepository.getById(6), 1);
        simpleOrder4.addProduct(productRepository.getById(7), 2);

        CompoundOrder compoundOrder = new CompoundOrder(specs1, 2, List.of(simpleOrder3, simpleOrder4));
        orderRepository.createCompoundOrder(compoundOrder);

        return orderRepository;
    }
}
