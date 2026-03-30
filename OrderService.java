package com.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.model.Cart;
import com.model.CartItem;
import com.model.Order;
import com.model.OrderStatus;
import com.model.Product;

public class OrderService {

    private CartService cartService;
    private ProductService productService;
    private PaymentService paymentService;

    private Map<String, Order> orders = new HashMap<>();

    public OrderService(CartService cs, ProductService ps, PaymentService pay) {
        this.cartService = cs;
        this.productService = ps;
        this.paymentService = pay;
    }

    public void placeOrder(String userId) {

        Cart cart = cartService.getCart(userId);
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Empty cart");
            return;
        }

        double total = cart.getTotal();

        Order order = new Order(UUID.randomUUID().toString(),
                userId,
                new ArrayList<>(cart.getItems().values()),
                total);

        order.setStatus(OrderStatus.PENDING_PAYMENT);

        if (!paymentService.pay()) {
            order.setStatus(OrderStatus.FAILED);
            System.out.println("Payment Failed");
            return;
        }

        for (CartItem item : cart.getItems().values()) {
            Product p = productService.getProduct(item.getProductId());
            p.setStock(p.getStock() - item.getQuantity());
            p.setReserved(p.getReserved() - item.getQuantity());
        }

        order.setStatus(OrderStatus.PAID);
        cart.clear();

        orders.put(order.getOrderId(), order);
        System.out.println("Order Success!");
    }

    public void viewOrders() {
        orders.values().forEach(System.out::println);
    }
}