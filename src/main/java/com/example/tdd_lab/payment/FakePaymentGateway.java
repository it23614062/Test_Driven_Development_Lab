package com.example.tdd_lab.payment;
public class FakePaymentGateway implements PaymentGateway {
    private final boolean shouldSucceed;

    public FakePaymentGateway(boolean shouldSucceed) {
        this.shouldSucceed = shouldSucceed;
    }

    @Override
    public void charge(double amount, String token) {
        if (!shouldSucceed) {
            throw new RuntimeException("payment failed");
        }
    }
}
