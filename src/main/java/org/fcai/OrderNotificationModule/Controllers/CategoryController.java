package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.Exceptions.CategoryNotFoundException;
import org.fcai.OrderNotificationModule.Exceptions.ProductNotFoundException;
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

    @GetMapping("/get-all")
    public List<Category> getAllCategories() {
        return context.categoryRepository.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public Category getCategoryById(@PathVariable int id) {
        Category category = context.categoryRepository.getCategory(id);
        if (category == null)
            throw new CategoryNotFoundException(id);
        return category;
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
        Product currentProduct = context.productRepository.getById(productId);
        if (currentProduct == null)
            throw new ProductNotFoundException(productId);
        context.categoryRepository.addProductToCategory(categoryId, currentProduct);
    }

    @GetMapping("/get-products-by-category-id/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return context.categoryRepository.getProductsByCategory(categoryId);
    }
}
