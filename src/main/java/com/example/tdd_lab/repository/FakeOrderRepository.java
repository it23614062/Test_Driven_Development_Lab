package com.example.tdd_lab.repository;
import com.example.tdd_lab.model.Order;

import java.util.ArrayList;
import java.util.List;

public class FakeOrderRepository implements OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    public int count() {
        return orders.size();
    }

    public List<Order> findAll() {
        return orders;
    }
}