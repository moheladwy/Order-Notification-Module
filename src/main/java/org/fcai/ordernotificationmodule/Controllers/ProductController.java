package org.fcai.ordernotificationmodule.Controllers;

import org.fcai.ordernotificationmodule.Models.Product;
import org.fcai.ordernotificationmodule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {
    private final DbContext context;

    @Autowired
    public ProductController(DbContext context) {
        this.context = context;
    }

    @GetMapping("/products/get-all")
    public List<Product> getAllProducts() {
        return context.productRepository.getAll();
    }

    @GetMapping("/products/get-by-category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return context.productRepository.getByCategory(categoryId);
    }

    @GetMapping("/products/get-by-id/{id}")
    public Product getProductById(@PathVariable int id) {
        return context.productRepository.getById(id);
    }

    @PostMapping("/products/add")
    public void addProduct(@RequestBody Product product) {
        context.productRepository.add(product);
    }

    @PutMapping("/products/update-quantity/{id}/{quantity}")
    public void updateProductQuantity(@PathVariable int id, @PathVariable int quantity) {
        context.productRepository.updateQuantity(id, quantity);
    }

    @PutMapping("/products/update-price/{id}/{price}")
    public void updateProductPrice(@PathVariable int id, @PathVariable double price) {
        context.productRepository.updatePrice(id, price);
    }

    @PutMapping("/products/update-category/{id}/{categoryId}")
    public void updateProductCategory(@PathVariable int id, @PathVariable int categoryId) {
        context.productRepository.updateCategory(id, categoryId);
    }

    @DeleteMapping("/products/remove/{id}")
    public void removeProduct(@PathVariable int id) {
        context.productRepository.remove(id);
    }
}
