package org.fcai.ordernotificationmodule.Controllers;

import org.fcai.ordernotificationmodule.Models.Category;
import org.fcai.ordernotificationmodule.Models.Product;
import org.fcai.ordernotificationmodule.Repositories.DbContext;
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

    @DeleteMapping("/remove-product-from-category/{categoryId}/{productId}")
    public void removeProductFromCategory(@PathVariable int categoryId, @PathVariable int productId) {
        context.categoryRepository.removeProductFromCategory(categoryId, productId);
    }

    @GetMapping("/get-products-by-category-id/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return context.categoryRepository.getProductsByCategory(categoryId);
    }

    @PutMapping("/update-product-quantity-in-category/{categoryId}/{productId}/{quantity}")
    public void updateProductQuantityInCategory(@PathVariable int categoryId, @PathVariable int productId, @PathVariable int quantity) {
        context.categoryRepository.updateProductQuantityInCategory(categoryId, productId, quantity);
    }
}
