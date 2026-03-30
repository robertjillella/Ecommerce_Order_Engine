package com.model;


public class Product {
    private final String productId;
    private final String name;
    private final double price;
    private int stock;
    private int reserved;

    public Product(String productId, String name, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reserved = 0;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public int getReserved() { return reserved; }

    public int getAvailableStock() {
        return stock - reserved;
    }

    public void setStock(int stock) { this.stock = stock; }
    public void setReserved(int reserved) { this.reserved = reserved; }
}