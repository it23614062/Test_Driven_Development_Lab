package com.example.tdd_lab.service;
import com.example.tdd_lab.cart.Cart;
import com.example.tdd_lab.dto.CheckoutResult;
import com.example.tdd_lab.model.Order;
import com.example.tdd_lab.payment.PaymentGateway;
import com.example.tdd_lab.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.Map;

public class CheckoutService {
    private final InventoryService inventoryService;
    private final PaymentGateway paymentGateway;
    private final DiscountEngine discountEngine;
    private final OrderRepository orderRepository;

    public CheckoutService(
            InventoryService inventoryService,
            PaymentGateway paymentGateway,
            DiscountEngine discountEngine,
            OrderRepository orderRepository
    ) {
        this.inventoryService = inventoryService;
        this.paymentGateway = paymentGateway;
        this.discountEngine = discountEngine;
        this.orderRepository = orderRepository;
    }

    public CheckoutResult checkout(Cart cart, String token) {
        for (Map.Entry<String, Integer> entry : cart.getItems().entrySet()) {
            if (entry.getValue() > inventoryService.getAvailable(entry.getKey())) {
                return new CheckoutResult(false, "insufficient inventory", 0);
            }
        }

        double amount = discountEngine.finalTotal(cart);

        try {
            paymentGateway.charge(amount, token);
        } catch (RuntimeException e) {
            return new CheckoutResult(false, e.getMessage(), 0);
        }

        Order order = new Order(cart.getItems(), amount, LocalDateTime.now().toString());
        orderRepository.save(order);

        return new CheckoutResult(true, "checkout complete", amount);
    }
}
