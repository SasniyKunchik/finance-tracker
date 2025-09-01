package org.example.financetracker.entity.enums;


import lombok.Getter;

@Getter
public enum RefundTransactionStatus {
    SUCCESS,
    FAILED;

    public static RefundTransactionStatus RefundTransactionStatusFromString(String status) {
        for (RefundTransactionStatus refundTransactionStatus : RefundTransactionStatus.values()) {
            if (refundTransactionStatus.name().equalsIgnoreCase(status)) {
                return refundTransactionStatus;
            }
        }
        throw new IllegalArgumentException("Unknown refund transaction status: " + status);
    }
}
