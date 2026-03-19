package com.example.tdd_lab.service;
import com.example.tdd_lab.cart.Cart;
import com.example.tdd_lab.model.Product;

import java.util.Map;

public class DiscountEngine {

    public double finalTotal(Cart cart) {
        double subtotal = cart.total();
        double total = subtotal;

        for (Map.Entry<String, Integer> entry : cart.getItems().entrySet()) {
            String sku = entry.getKey();
            int qty = entry.getValue();

            if (qty >= 10) {
                Product product = cart.getCatalog().findBySku(sku);
                total -= product.getPrice() * qty * 0.10;
            }
        }

        if (subtotal >= 1000) {
            total -= subtotal * 0.05;
        }

        return total;
    }
}