package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Exceptions.CategoryNotFoundException;
import org.fcai.OrderNotificationModule.Models.Category;
import org.fcai.OrderNotificationModule.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    private final List<Category> allCategories;

    public CategoryRepository() {
        this.allCategories = new ArrayList<>();
    }

    public List<Category> getAll() {
        return allCategories;
    }

    public void addNewCategory(Category category) {
        if(category == null)
            throw new NullPointerException("Category can't be null");
        allCategories.add(category);
    }

    public void removeCategory(int categoryId) {
        Category currentCategory = getCategory(categoryId);

        if(currentCategory == null)
            throw new CategoryNotFoundException(categoryId);

        allCategories.remove(currentCategory);
    }

    public void addProductToCategory(int categoryId, Product product) {
        Category currentCategory = getCategory(categoryId);

        if(currentCategory == null)
            throw new CategoryNotFoundException(categoryId);

        currentCategory.addProduct(product);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        Category currentCategory = getCategory(categoryId);

        if(currentCategory == null)
            throw new CategoryNotFoundException(categoryId);

        return currentCategory.getProducts();
    }

    public Category getCategory(int categoryId) {
        //This loops till it finds the corresponding category
        for (Category currentCategory : allCategories) {
            if (currentCategory.getId() == categoryId)
                return currentCategory;
        }
        //In case the category is not found.
        return null;
    }
}
