package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Models.Category;
import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final DbContext context;

    @Autowired
    public CategoryController(DbContext context) {
        this.context = context;
    }

    @GetMapping("/get-all")
    public List<Category> getAllCategories() {
        return context.categoryRepository.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return context.categoryRepository.getById(id);
    }

    @PostMapping("/add-new-category")
    public void addCategory(@RequestBody Category category) {
        context.categoryRepository.addNewCategory(category);
    }

    @DeleteMapping("/remove/{id}")
    public void removeCategory(@PathVariable int id) {
        context.categoryRepository.removeCategory(id);
    }

    @PostMapping("/add-product-to-category/{categoryId}/{productId}")
    public void addProductToCategory(@PathVariable int categoryId, @PathVariable int productId) {
        context.categoryRepository.addProductToCategory(categoryId, productId);
    }

    @GetMapping("/get-products-by-category-id/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return context.categoryRepository.getProductsByCategory(categoryId);
    }
}
