package com.example.tdd_lab.repository;
import com.example.tdd_lab.model.Order;

public interface OrderRepository {
    void save(Order order);
}