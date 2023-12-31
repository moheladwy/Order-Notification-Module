package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Enums.CategoryName;
import org.fcai.OrderNotificationModule.Enums.OrderStatus;
import org.fcai.OrderNotificationModule.Models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbContext {
    public final UserRepository userRepository;
    public final ProductRepository productRepository;
    public final CategoryRepository categoryRepository;
    public final OrderRepository orderRepository;
    public final NotificationRepository notificationRepository;

    public DbContext() {
        try {
            userRepository = initializeUserRepository();
            productRepository = initializeProductRepository();
            categoryRepository = initializeCategoryRepository();
            orderRepository = initializeOrderRepository();
            notificationRepository = new NotificationRepository();
        } catch (Exception e) {
            System.err.println("Error initializing repositories: " + e.getMessage());
            throw e;
        }
    }

    private static UserRepository initializeUserRepository() {
        UserRepository userRepository = new UserRepository();

        userRepository.registerNewUser(new User("user1", "User One", "user1@example.com", "password1", "Address1", "123456789", "Area1", 10000.0));
        userRepository.registerNewUser(new User("user2", "User Two", "user2@example.com", "password2", "Address2", "987654321", "Area2", 10000.0));
        userRepository.registerNewUser(new User("user3", "User Three", "user3@example.com", "password3", "Address3", "123456789", "Area3", 10000.0));
        userRepository.registerNewUser(new User("user4", "User Four", "user4@example.com", "password4", "Address4", "987654321", "Area4", 10000.0));

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
        productRepository.add(new Product(11,"Chairs","","Adham",1000),34);
        productRepository.add(new Product(12,"Vitamins and supplements","","Adham",500),88 );
        productRepository.add(new Product(13,"Sports accessories","","Adham",500),98 );
        productRepository.add(new Product(14,"Dolls","","Omar",200),33 );
        productRepository.add(new Product(15,"Printers","","Omar",5000),33 );
        productRepository.add(new Product(16,"Pens","","Omar",5000),33 );
        productRepository.add(new Product(17,"Home Appliances","","Adham",500),88 );
        productRepository.add(new Product(18,"Cleaning Supplies","","Adham",500),98 );
        productRepository.add(new Product(19,"Kitchen Appliances","","Adham",500),98 );
        productRepository.add(new Product(20,"Outdoor lighting","","Adham",500),98 );
        productRepository.add(new Product(21,"Outdoor furniture","","Adham",500),98 );
        productRepository.add(new Product(22,"Dumbbell","","Adham",500),98 );
        productRepository.add(new Product(23,"Gift Card","","Adham",500),98 );

        return productRepository;

    }

    // ASSIGN: Adham
    private CategoryRepository initializeCategoryRepository() {
        CategoryRepository categoryRepository = new CategoryRepository();

        categoryRepository.addNewCategory(new Category(1, CategoryName.FOOD, "Soft drinks, coffees, teas, beers, and ales"));
        categoryRepository.addProductToCategory(1, productRepository.getById(8));
        categoryRepository.addNewCategory(new Category(2, CategoryName.FURNITURE, "Chairs, tables, beds, desks, dressers, and cupboards"));
        categoryRepository.addProductToCategory(2, productRepository.getById(9));
        categoryRepository.addProductToCategory(2, productRepository.getById(10));
        categoryRepository.addProductToCategory(2, productRepository.getById(11));
        categoryRepository.addNewCategory(new Category(3, CategoryName.GROCERIES, "Bread, cheese, eggs, milk, and other dairy products"));
        categoryRepository.addProductToCategory(3, productRepository.getById(6));
        categoryRepository.addProductToCategory(3, productRepository.getById(7));
        categoryRepository.addNewCategory(new Category(4, CategoryName.HEALTH, "Medicines, vitamins, bandages, and other medical supplies"));
        categoryRepository.addProductToCategory(4, productRepository.getById(12));
        categoryRepository.addNewCategory(new Category(5, CategoryName.HOME, "Cleaning supplies, cooking utensils, and various other household items"));
        categoryRepository.addProductToCategory(5, productRepository.getById(17));
        categoryRepository.addProductToCategory(5, productRepository.getById(18));
        categoryRepository.addNewCategory(new Category(6, CategoryName.KITCHEN, "Pots, pans, dishes, cutlery, and various other kitchen items"));
        categoryRepository.addProductToCategory(6, productRepository.getById(19));
        categoryRepository.addNewCategory(new Category(7, CategoryName.OFFICE, "Pens, pencils, paper, binders, and other office supplies"));
        categoryRepository.addProductToCategory(7, productRepository.getById(15));
        categoryRepository.addProductToCategory(7, productRepository.getById(16));
        categoryRepository.addNewCategory(new Category(8, CategoryName.OUTDOORS, "Lawn mowers, shovels, rakes, and other outdoor items"));
        categoryRepository.addProductToCategory(8, productRepository.getById(20));
        categoryRepository.addProductToCategory(8, productRepository.getById(21));
        categoryRepository.addNewCategory(new Category(9, CategoryName.SPORTS, "Balls, bats, gloves, and various other sports items"));
        categoryRepository.addProductToCategory(9, productRepository.getById(13));
        categoryRepository.addProductToCategory(9, productRepository.getById(22));
        categoryRepository.addNewCategory(new Category(10, CategoryName.TOYS, "Dolls, board games, puzzles, and various other toys"));
        categoryRepository.addProductToCategory(10, productRepository.getById(14));
        categoryRepository.addNewCategory(new Category(11, CategoryName.OTHER, "Various other items"));
        categoryRepository.addProductToCategory(11, productRepository.getById(23));

        return categoryRepository;
    }

    private OrderRepository initializeOrderRepository() {
        OrderRepository orderRepository = new OrderRepository();
        OrderSpecs specs1 = new OrderSpecs(1, userRepository.getUserByUsername("user1"), 20.0, OrderStatus.OrderPlaced);
        OrderSpecs specs2 = new OrderSpecs(2, userRepository.getUserByUsername("user2"), 30.0, OrderStatus.OrderPlaced);

        SimpleOrder simpleOrder1 = new SimpleOrder(specs1);
        simpleOrder1.addProduct(productRepository.getById(1), 2);
        simpleOrder1.addProduct(productRepository.getById(2), 3);

        SimpleOrder simpleOrder2 = new SimpleOrder(specs2);
        simpleOrder2.addProduct(productRepository.getById(3), 1);

        orderRepository.createSimpleOrder(simpleOrder1);
        orderRepository.createSimpleOrder(simpleOrder2);


        SimpleOrder simpleOrder3 = new SimpleOrder(new OrderSpecs(3, userRepository.getUserByUsername("user3"), 40.0, OrderStatus.OrderPlaced));
        SimpleOrder simpleOrder4 = new SimpleOrder(new OrderSpecs(4, userRepository.getUserByUsername("user4"), 50.0, OrderStatus.OrderPlaced));

        simpleOrder3.addProduct(productRepository.getById(4), 2);
        simpleOrder3.addProduct(productRepository.getById(5), 3);
        
        simpleOrder4.addProduct(productRepository.getById(6), 1);
        simpleOrder4.addProduct(productRepository.getById(7), 2);

        CompoundOrder compoundOrder = new CompoundOrder(
                new OrderSpecs(5, userRepository.getUserByUsername("user1"), 60.0, OrderStatus.OrderPlaced), List.of(simpleOrder3, simpleOrder4));
        orderRepository.createCompoundOrder(compoundOrder);

        return orderRepository;
    }
}
