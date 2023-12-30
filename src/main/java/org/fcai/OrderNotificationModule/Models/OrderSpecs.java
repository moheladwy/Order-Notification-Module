package org.fcai.OrderNotificationModule.Models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderSpecs {
    private int id;
    private Map<Product, Integer> products;
    private double productsPrice;
    private double shippingFees;
    private User user;
    private final LocalDateTime creationDate;

    public OrderSpecs(int id, User user, double shippingFees) {
        try {
            setId(id);
            setShippingFees(shippingFees);
            setUser(user);
            products = new HashMap<>();
            productsPrice = 0;
            creationDate = LocalDateTime.now();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to make order specs: " + e.getMessage());
            throw e;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(double shippingFees) {
        if (shippingFees <= 0)
            throw new IllegalArgumentException("Shipping Fees must be positive number");
        this.shippingFees = shippingFees;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        if (products == null)
            throw new NullPointerException("Products cannot be null");
        this.products = products;
    }

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        if (productsPrice <= 0)
            throw new IllegalArgumentException("Products Price must be positive number");
        this.productsPrice = productsPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user == null)
            throw new NullPointerException("User cannot be null");
        this.user = user;
    }

    public void addProduct(Product product, int quantity) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive number");
        if (products.containsKey(product))
            products.put(product, products.get(product) + quantity);
        else products.put(product, quantity);
        productsPrice += (product.getPrice() * quantity);
    }

    public void removeProduct(Product product, int quantity) {
        if (product == null)
            throw new NullPointerException("Product cannot be null");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be positive number");
        if (products.containsKey(product)) {
            if (products.get(product) < quantity)
                throw new IllegalArgumentException("Quantity must be less than or equal to the quantity of the product");
            products.put(product, products.get(product) - quantity);
            productsPrice -= (product.getPrice() * quantity);
        } else throw new IllegalArgumentException("Product does not exist in the order");
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

}
