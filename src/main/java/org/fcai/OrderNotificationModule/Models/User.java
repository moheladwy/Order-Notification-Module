package org.fcai.OrderNotificationModule.Models;

public class User {
    private String username;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private String area;
    private double balance;

    public User(String username, String name, String email, String password, String address, String phoneNumber, String area, double balance) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setName(name);
        setArea(area);
        setAddress(address);
        setBalance(balance);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null)
            throw new NullPointerException("Username cannot be null!");
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("Name cannot be null!");
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null)
            throw new NullPointerException("Email cannot be null!");
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null)
            throw new NullPointerException("Password cannot be null!");
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null)
            throw new NullPointerException("Address cannot be null!");
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null)
            throw new NullPointerException("Phone number cannot be null!");
        this.phoneNumber = phoneNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        if (area == null)
            throw new NullPointerException("Area cannot be null!");
        this.area = area;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0)
            throw new IllegalArgumentException("Balance cannot be negative!");
        this.balance = balance;
    }
}
