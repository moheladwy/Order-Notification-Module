package org.fcai.ordernotificationmodule.Models;

public class Product {
    private String serialNumber;
    private String name;
    private String description;
    private String vendor;
    private double price;

    public Product(String serialNumber, String name, String description, String vendor, double price) {
        setSerialNumber(serialNumber);
        setName(name);
        setDescription(description);
        setVendor(vendor);
        setPrice(price);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        if (serialNumber == null)
            throw new NullPointerException("Serial number cannot be null");
        this.serialNumber = serialNumber;
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
