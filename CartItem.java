package com.model;


public class CartItem {
    private final String productId;
    private final String productName;
    private int quantity;
    private final double unitPrice;

    public CartItem(String productId, String productName, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    public double getSubtotal() {
        return quantity * unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}