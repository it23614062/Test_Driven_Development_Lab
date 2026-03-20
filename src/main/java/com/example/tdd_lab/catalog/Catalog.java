package com.example.tdd_lab.catalog;
import com.example.tdd_lab.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private final Map<String, Product> products = new HashMap<>();

    public void add(Product product) {
        products.put(product.getSku(), product);
    }

    public Product findBySku(String sku) {
        return products.get(sku);
    }
}