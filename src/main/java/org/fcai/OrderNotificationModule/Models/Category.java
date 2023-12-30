package org.fcai.OrderNotificationModule.Models;

import org.fcai.OrderNotificationModule.Enums.CategoryName;

import java.util.List;

public class Category {
    private int id;
    private CategoryName name;
    private String description;
    private List<Product> products;

    public Category(int id, CategoryName name, String description, List<Product> products) {
        try {
            setId(id);
            setName(name);
            setDescription(description);
            setProducts(products);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to make category: " + e.getMessage());
            throw e;
        }
    }

    public Category(int id, CategoryName categoryName, String description) {
        try {
            setId(id);
            setName(categoryName);
            setDescription(description);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to make category: " + e.getMessage());
            throw e;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0)
            throw new NullPointerException("Serial number must be bigger than zero");
        this.id = id;
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

    // TODO: to be reimplemented again.
    public void removeProduct(Product product) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");

        if(!products.remove(product))
            throw new IllegalArgumentException("Product does not exist in this category");

        products.remove(product);
    }
}
