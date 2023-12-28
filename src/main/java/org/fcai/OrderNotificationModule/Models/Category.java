package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.CategoryName;

import java.util.HashMap;
import java.util.List;

public class Category {
    private int serialNumber;
    private CategoryName name;
    private String description;
    private List<Product> products;

    public Category(int serialNumber, CategoryName name, String description, List<Product> products) {
        setSerialNumber(serialNumber);
        setName(name);
        setDescription(description);
        setProducts(products);
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        if (serialNumber <= 0)
            throw new NullPointerException("Serial number must be bigger than zero");
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        if (products == null)
            throw new NullPointerException("Products cannot be null");
        this.products = products;
    }

    public void addProduct(Product product) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");

        products.add(product);
    }

    public void removeProduct(Product product) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");

        if(!products.remove(product))
            throw new IllegalArgumentException("Product does not exist in this category");

        products.remove(product);
    }
}
