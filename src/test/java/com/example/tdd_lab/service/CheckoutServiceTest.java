package com.example.tdd_lab.service;
import com.example.tdd_lab.cart.Cart;
import com.example.tdd_lab.catalog.Catalog;
import com.example.tdd_lab.dto.CheckoutResult;
import com.example.tdd_lab.model.Product;
import com.example.tdd_lab.payment.FakePaymentGateway;
import com.example.tdd_lab.repository.FakeOrderRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutServiceTest {

    @Test
    void successfulCheckoutReturnsSuccess() {
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

        CheckoutResult result = service.checkout(cart, "tok_123");

        assertTrue(result.isSuccess());
    }

    @Test
    void failedPaymentReturnsErrorAndDoesNotSaveOrder() {
        Catalog catalog = new Catalog();
        catalog.add(new Product("SKU1", "Phone", 100));

        InventoryService inventory = sku -> 5;
        Cart cart = new Cart(catalog, inventory);
        cart.addItem("SKU1", 1);

        FakeOrderRepository repo = new FakeOrderRepository();

        CheckoutService service = new CheckoutService(
                inventory,
                new FakePaymentGateway(false),
                new DiscountEngine(),
                repo
        );

        CheckoutResult result = service.checkout(cart, "tok_123");

        assertFalse(result.isSuccess());
        assertEquals("payment failed", result.getMessage());
        assertEquals(0, repo.count());
    }
}
