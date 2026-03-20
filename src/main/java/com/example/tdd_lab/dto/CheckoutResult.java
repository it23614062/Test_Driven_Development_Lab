package com.example.tdd_lab.dto;
public class CheckoutResult {
    private final boolean success;
    private final String message;
    private final double total;

    public CheckoutResult(boolean success, String message, double total) {
        this.success = success;
        this.message = message;
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public double getTotal() {
        return total;
    }
}