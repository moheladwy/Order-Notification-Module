package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final DbContext context;

    @Autowired
    public ProductController(DbContext context) {
        this.context = context;
    }

    @GetMapping("/get-all")
    public HashMap<Product,Integer> getAllProducts() {
        return context.productRepository.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Product getProductById(@PathVariable int id) {
        return context.productRepository.getById(id);
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product,@RequestBody int quantity) {
        context.productRepository.add(product, quantity);
    }

    @PutMapping("/update-quantity/{id}/{quantity}")
    public void updateProductQuantity(@PathVariable int id, @PathVariable int quantity) {
        context.productRepository.updateQuantity(id, quantity);
    }
    
    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable int id) {
        context.productRepository.remove(id);
    }
}
