package org.example.financetracker.entity.enums;


import lombok.Getter;

@Getter
public enum PaymentTransactionStatus {
    PROCESSING,
    OK,
    FAILED;

    public static PaymentTransactionStatus getTransactionStatusFromString(String status) {
        for(PaymentTransactionStatus paymentTransactionStatus : PaymentTransactionStatus.values()) {
            if(paymentTransactionStatus.name().equalsIgnoreCase(status)) {
                return paymentTransactionStatus;
            }
        }
        throw new IllegalArgumentException("Unknown transaction status: " + status);
    }
}
