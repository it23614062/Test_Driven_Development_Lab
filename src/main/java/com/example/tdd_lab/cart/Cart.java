package com.example.tdd_lab.cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Catalog catalog;
    private final Map<String, Integer> items = new HashMap<>();

    public Cart(Catalog catalog) {
        this.catalog = catalog;
    }

    public void addItem(String sku, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }

        Product product = catalog.findBySku(sku);
        if (product == null) {
            throw new IllegalArgumentException("product not found");
        }

        items.put(sku, items.getOrDefault(sku, 0) + quantity);
    }

    public void removeItem(String sku) {
        items.remove(sku);
    }

    public double total() {
        double total = 0;

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            Product product = catalog.findBySku(entry.getKey());
            total += product.getPrice() * entry.getValue();
        }

        return total;
    }
}