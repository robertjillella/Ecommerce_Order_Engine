package com.model;

import java.util.*;

public class Order {
    private final String orderId;
    private final String userId;
    private final List<CartItem> items;
    private double total;
    private OrderStatus status;

    public Order(String orderId, String userId, List<CartItem> items, double total) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = new ArrayList<>(items);
        this.total = total;
        this.status = OrderStatus.CREATED;
    }

    public String getOrderId() { return orderId; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    @Override
    public String toString() {
        return orderId + " | " + status + " | " + total;
    }
}