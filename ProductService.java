package com.service;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.model.Product;

public class ProductService {

    private Map<String, Product> products = new ConcurrentHashMap<>();

    public void addProduct(String id, String name, double price, int stock) {
        if (products.containsKey(id)) {
            System.out.println("Duplicate product!");
            return;
        }
        products.put(id, new Product(id, name, price, stock));
    }

    public Product getProduct(String id) {
        return products.get(id);
    }

    public void viewProducts() {
        products.values().forEach(p ->
                System.out.println(p.getProductId() + " " + p.getName() + " Stock:" + p.getStock()));
    }
}