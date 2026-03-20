package com.example.tdd_lab.cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.model.Product;
import com.example.tdd_lab.service.InventoryService;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Catalog catalog;
    private final InventoryService inventoryService;
    private final Map<String, Integer> items = new HashMap<>();

    public Cart(Catalog catalog) {
        this(catalog, null);
    }

    public Cart(Catalog catalog, InventoryService inventoryService) {
        this.catalog = catalog;
        this.inventoryService = inventoryService;
    }

    public void addItem(String sku, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }

        Product product = catalog.findBySku(sku);
        if (product == null) {
            throw new IllegalArgumentException("product not found");
        }

        if (inventoryService != null) {
            int available = inventoryService.getAvailable(sku);
            int current = items.getOrDefault(sku, 0);
            if (current + quantity > available) {
                throw new IllegalArgumentException("insufficient inventory");
            }
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

    public Map<String, Integer> getItems() {
        return items;
    }

    public Catalog getCatalog() {
        return catalog;
    }
}