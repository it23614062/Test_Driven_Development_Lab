package com.example.tdd_lab.repository;
import com.example.tdd_lab.cart.Cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.model.Product;
import com.example.tdd_lab.payment.FakePaymentGateway;
import com.example.tdd_lab.service.CheckoutService;
import com.example.tdd_lab.service.DiscountEngine;
import com.example.tdd_lab.service.InventoryService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderPersistenceTest {

    @Test
    void successfulCheckoutCreatesOrder() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        InventoryService inventory = sku -> 5;
        Cart cart = new Cart(catalog, inventory);
        cart.addItem("SKU1", 2);

        FakeOrderRepository repo = new FakeOrderRepository();

        CheckoutService service = new CheckoutService(
                inventory,
                new FakePaymentGateway(true),
                new DiscountEngine(),
                repo
        );

        service.checkout(cart, "tok_123");

        assertEquals(1, repo.count());
    }
}