package org.example.financetracker.entity;


import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.financetracker.entity.enums.RefundTransactionStatus;
import org.example.financetracker.entity.enums.convert.RefundTransactionStatusConverter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRefund extends BasicEntity {
    private BigDecimal refundedAmount;
    private String reason;

    @Convert(converter = RefundTransactionStatusConverter.class)
    private RefundTransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "paymentTransactionId", referencedColumnName = "id")
    private PaymentTransaction paymentTransaction;



}
