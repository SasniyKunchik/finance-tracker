package org.example.financetracker.service.handler;

public interface PaymentTransactionCommandHandler {
    void process(String requestId, String message);
}
