package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Exceptions.ProductNotFoundException;
import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final DbContext context;

    @Autowired
    public ProductController(DbContext context) {
        this.context = context;
    }

    @GetMapping("/get-all")
    public List<Product> getAllProducts() {
        Map<Product, Integer> products = context.productRepository.getAll();
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productList.add(entry.getKey());
        }

        return productList;
    }

    @GetMapping("/get-by-id/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = context.productRepository.getById(id);
        if (product == null)
            throw new ProductNotFoundException(id);
        return product;
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody Product product, @RequestBody int quantity) {
        try {
            context.productRepository.add(product, quantity);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to add product: " + e.getMessage());
            throw e;
        }
    }

    @PutMapping("/update-quantity/{id}/{quantity}")
    public void updateProductQuantity(@PathVariable int id, @PathVariable int quantity) {
        try {
            context.productRepository.updateQuantity(id, quantity);
        } catch (ProductNotFoundException e) {
            System.err.println("Failed to update product quantity: " + e.getMessage());
            throw e;
        }
    }
    
    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable int id) {
        try {
            context.productRepository.remove(id);
        } catch (ProductNotFoundException e) {
            System.err.println("Failed to remove product: " + e.getMessage());
            throw e;
        }
    }
}
