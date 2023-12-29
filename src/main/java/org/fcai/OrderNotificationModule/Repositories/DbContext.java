package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.Product;
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
        return new CategoryRepository(); // TODO: Implement CategoryRepository Initialization
    }

    private static OrderRepository initializeOrderRepository() {
        return new OrderRepository(); // TODO: Implement OrderRepository Initialization
    }
}
