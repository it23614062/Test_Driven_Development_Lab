package com.example.tdd_lab.service;
import com.example.tdd_lab.cart.Cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountEngineTest {

    @Test
    void bulkDiscountAppliesTenPercentToLine() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        Cart cart = new Cart(catalog);
        cart.addItem("SKU1", 10);

        DiscountEngine engine = new DiscountEngine();
        assertEquals(900, engine.finalTotal(cart));
    }

    @Test
    void orderDiscountAppliesFivePercentWhenTotalAtLeast1000() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Laptop", 1000));

        Cart cart = new Cart(catalog);
        cart.addItem("SKU1", 1);

        DiscountEngine engine = new DiscountEngine();
        assertEquals(950, engine.finalTotal(cart));
    }
}
