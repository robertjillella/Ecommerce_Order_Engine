package com.service;


import java.util.HashMap;
import java.util.Map;

import com.model.Cart;
import com.model.CartItem;
import com.model.Product;

public class CartService {

    private Map<String, Cart> carts = new HashMap<>();
    private ProductService productService;

    public CartService(ProductService ps) {
        this.productService = ps;
    }

    public void addItem(String userId, String productId, int qty) {

        Product p = productService.getProduct(productId);
        if (p == null || p.getAvailableStock() < qty) {
            System.out.println("Stock not available!");
            return;
        }

        p.setReserved(p.getReserved() + qty);

        Cart cart = carts.computeIfAbsent(userId, Cart::new);

        cart.getItems().merge(productId,
                new CartItem(productId, p.getName(), qty, p.getPrice()),
                (oldVal, newVal) -> {
                    oldVal.setQuantity(oldVal.getQuantity() + qty);
                    return oldVal;
                });
    }

    public Cart getCart(String userId) {
        return carts.get(userId);
    }

    public void viewCart(String userId) {
        Cart cart = carts.get(userId);
        if (cart == null) return;

        cart.getItems().values().forEach(i ->
                System.out.println(i.getProductName() + " x " + i.getQuantity()));
    }
}