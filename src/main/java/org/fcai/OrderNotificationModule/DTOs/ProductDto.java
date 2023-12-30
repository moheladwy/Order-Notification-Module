package org.fcai.OrderNotificationModule.DTOs;

public class ProductDto {
    public int id;
    public String name;
    public String description;
    public String vendor;
    public double price;
    public int quantity;

    public ProductDto(int id, String name, String description, String vendor, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.vendor = vendor;
        this.price = price;
        this.quantity = quantity;
    }
}
