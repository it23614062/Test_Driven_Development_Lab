package com.example.tdd_lab.cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.model.Product;
import com.example.tdd_lab.service.InventoryService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartInventoryTest {

    @Test
    void addItemFailsWhenQuantityExceedsInventory() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        InventoryService inventory = sku -> 2;
        Cart cart = new Cart(catalog, inventory);

        assertThrows(IllegalArgumentException.class, () -> cart.addItem("SKU1", 3));
    }
}