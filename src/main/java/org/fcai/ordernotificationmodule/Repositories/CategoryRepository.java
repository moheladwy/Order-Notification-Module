package org.fcai.ordernotificationmodule.Repositories;

import org.fcai.ordernotificationmodule.Models.Category;
import org.fcai.ordernotificationmodule.Models.Product;

import java.util.List;

public class CategoryRepository {
    public List<Category> getAll() {
        return null; // TODO: Implement this method.
    }

    public Category getById(int id) {
        return null; // TODO: Implement this method.
    }

    public void addNewCategory(Category category) {
        // TODO: Implement this method.
    }

    public void removeCategory(int id) {
        // TODO: Implement this method.
    }

    public void addProductToCategory(int categoryId, int productId) {
        // TODO: Implement this method.
    }

    public void removeProductFromCategory(int categoryId, int productId) {
        // TODO: Implement this method.
    }

    public List<Product> getProductsByCategory(int categoryId) {
        return null; // TODO: Implement this method.
    }

    public void updateProductQuantityInCategory(int categoryId, int productId, int quantity) {
        // TODO: Implement this method.
    }
}
