package org.fcai.OrderNotificationModule.Models;

public class Product {
    private int id;
    private String name;
    private String description;
    private String vendor;
    private double price;

    public Product(int id, String name, String description, String vendor, double price) {
        try {
            setId(id);
            setName(name);
            setDescription(description);
            setVendor(vendor);
            setPrice(price);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("Failed to make product: " +e.getMessage());
            throw e;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        if (vendor == null)
            throw new NullPointerException("Vendor cannot be null");
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }
}
