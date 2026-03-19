package com.example.tdd_lab.model;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final Map<String, Integer> items;
    private final double total;
    private final String createdAt;

    public Order(Map<String, Integer> items, double total, String createdAt) {
        this.items = new HashMap<>(items);
        this.total = total;
        this.createdAt = createdAt;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
