package org.fcai.ordernotificationmodule.Models;

import java.util.HashMap;

public class Category {
    private String serialNumber;
    private CategoryName name;
    private String description;
    private HashMap<Product, Integer> products;

    public Category(String serialNumber, CategoryName name, String description, HashMap<Product, Integer> products) {
        setSerialNumber(serialNumber);
        setName(name);
        setDescription(description);
        setProducts(products);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        if (serialNumber == null)
            throw new NullPointerException("Serial number cannot be null");
        this.serialNumber = serialNumber;
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        if (name == null)
            throw new NullPointerException("Name cannot be null");
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null)
            throw new NullPointerException("Description cannot be null");
        this.description = description;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        if (products == null)
            throw new NullPointerException("Products cannot be null");
        this.products = products;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive number");
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive number");
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            if (currentQuantity - quantity == 0) {
                products.remove(product);
            } else if (currentQuantity - quantity > 0) {
                products.put(product, products.get(product) - quantity);
            } else {
                throw new IllegalArgumentException("Quantity must be less than or equal to current quantity");
            }
        } else {
            throw new IllegalArgumentException("Product does not exist in this category");
        }
    }
}
