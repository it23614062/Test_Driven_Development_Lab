package com.example.tdd_lab.cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    void addItemFromCatalog() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        Cart cart = new Cart(catalog);
        cart.addItem("SKU1", 2);

        assertEquals(200, cart.total());
    }

    @Test
    void addingUnknownProductThrowsError() {
        Cart cart = new Cart(new Catalog());

        assertThrows(IllegalArgumentException.class, () -> cart.addItem("UNKNOWN", 1));
    }

    @Test
    void quantityMustBePositive() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        Cart cart = new Cart(catalog);

        assertThrows(IllegalArgumentException.class, () -> cart.addItem("SKU1", 0));
    }

    @Test
    void removeItem() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        Cart cart = new Cart(catalog);
        cart.addItem("SKU1", 1);
        cart.removeItem("SKU1");

        assertEquals(0, cart.total());
    }
}