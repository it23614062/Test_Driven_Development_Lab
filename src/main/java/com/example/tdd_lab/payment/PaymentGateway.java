package com.example.tdd_lab.payment;
public interface PaymentGateway {
    void charge(double amount, String token);
}