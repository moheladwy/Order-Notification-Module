package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Exceptions.ProductNotFoundException;
import org.fcai.OrderNotificationModule.Models.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private final Map<Product, Integer> products;

    public ProductRepository() {
        products = new HashMap<>();
    }

    public Map<Product, Integer> getAll() {
        return products;
    }

    public Product getById(int id) {
        for (Product key : products.keySet()){
            if (key.getId() == id) {
               return key;
            }
        }
        return null;
    }

    public void add(Product product, int quantity) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");
        if (quantity < 1)
            throw new IllegalArgumentException("Quantity cannot be less than 1");
        products.put(product,quantity);
    }

    public void remove(int id) {
        for (Product key : products.keySet()){
            if (key.getId() == id) {
                products.remove(key);
                return;
            }
        }
        throw new ProductNotFoundException(id);
    }
    
    public void updateQuantity(int id, int quantity) {
        for (Product key : products.keySet()){
            if (key.getId() == id) {
                products.put(key,quantity);
                return;
            }
        }
        throw new ProductNotFoundException(id);
    }
}
