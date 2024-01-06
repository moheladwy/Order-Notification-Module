package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Exceptions.CategoryNotFoundException;
import org.fcai.OrderNotificationModule.Models.Category;
import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final DbContext context;

    @Autowired
    public CategoryController(DbContext context) {
        this.context = context;
    }

    // DONE.
    @GetMapping("/get-all")
    public List<Category> getAllCategories() {
        return context.categoryRepository.getAll();
    }

    // DONE.
    @GetMapping("/get-by-id/{id}")
    public Category getCategoryById(@PathVariable int id) {
        Category category = context.categoryRepository.getCategory(id);
        if (category == null)
            throw new CategoryNotFoundException(id);
        return category;
    }

    // DONE.
    @GetMapping("/get-products-by-category-id/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        try {
            return context.categoryRepository.getProductsByCategory(categoryId);
        } catch (CategoryNotFoundException e) {
            System.err.println("Failed to get products by category: " + e.getMessage());
            throw e;
        }
    }

    // DONE.
    @PostMapping("/add-new-category")
    public void addCategory(@RequestBody Category category) {
        try {
            context.categoryRepository.addNewCategory(category);
        } catch (NullPointerException e) {
            System.err.println("Failed to add category: " + e.getMessage());
            throw e;
        }
    }

    // DONE.
    @DeleteMapping("/remove/{id}")
    public void removeCategory(@PathVariable int id) {
        try {
            context.categoryRepository.removeCategory(id);
        } catch (CategoryNotFoundException e) {
            System.err.println("Failed to remove category: " + e.getMessage());
            throw e;
        }
    }
}
