package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Models.Category;
import org.fcai.OrderNotificationModule.Models.Product;

import java.util.List;

public class CategoryRepository {
    private List<Category> allCategories;


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
            throw new NullPointerException("Category can't be null");

        allCategories.remove(currentCategory);
    }

    public void addProductToCategory(int categoryId, Product product) {
        Category currentCategory = getCategory(categoryId);

        if(currentCategory == null)
            throw new NullPointerException("Category can't be null");

        currentCategory.addProduct(product);
    }

    public void removeProductFromCategory(int categoryId, Product product) {
        Category currentCategory = getCategory(categoryId);

        if(currentCategory == null || product == null)
            throw new NullPointerException("Category or product can't be null");

        currentCategory.removeProduct(product);
    }

    public List<Product> getProductsByCategory(int categoryId) {
        Category currentCategory = getCategory(categoryId);

        if(currentCategory == null)
            throw new NullPointerException("Category can't be null");

        return currentCategory.getProducts();
    }

    public Category getCategory(int categoryId){

        //This loops till it finds the corresponding category
        for (int i = 0; i < allCategories.size(); i++) {
            Category currentCategory = allCategories.get(i);
            if(currentCategory.getSerialNumber() == categoryId){
                return currentCategory;
            }
        }
        //In case the category is not found.
        return null;
    }
}
