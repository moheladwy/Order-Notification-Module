package org.fcai.OrderNotificationModule.Repositories;

import org.fcai.OrderNotificationModule.Exceptions.ProductNotFoundException;
import org.fcai.OrderNotificationModule.Models.Category;
import org.fcai.OrderNotificationModule.Models.Product;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ProductRepository {
    private HashMap<Product, Integer> products;
    public HashMap<Product,Integer> getAll() {
        return products;
    }

    public Product getById(int id) {
        for (Product key : products.keySet()){
            if (Objects.equals(key.getSerialNumber(), id)){
               return key;
            }
        }
        ProductNotFoundException exception= new ProductNotFoundException(id);
        return null;
    }

    public void add(Product product, int quantity) {
        products.put(product,quantity);
    }

    public void remove(int id) {
        for (Product key : products.keySet()){
            if (Objects.equals(key.getSerialNumber(), id)){
                products.remove(key);
                return;
            }
        }
        ProductNotFoundException exception= new ProductNotFoundException(id);
    }
    
    public void updateQuantity(int id, int quantity) {
        for (Product key : products.keySet()){
            if (Objects.equals(key.getSerialNumber(), id)){
                products.put(key,quantity);
                return;
            }
        }
        ProductNotFoundException exception= new ProductNotFoundException(id);
    }

 
}
