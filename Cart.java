package com.model;



import java.util.*;

public class Cart {
    private final String userId;
    private final Map<String, CartItem> items = new HashMap<>();
    private String couponCode;
    private double discount;

    public Cart(String userId) {
        this.userId = userId;
    }

    public Map<String, CartItem> getItems() { return items; }

    public double getSubtotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public double getTotal() {
        return Math.max(0, getSubtotal() - discount);
    }

    public void setCoupon(String code, double discount) {
        this.couponCode = code;
        this.discount = discount;
    }

    public void clear() {
        items.clear();
        couponCode = null;
        discount = 0;
    }
}