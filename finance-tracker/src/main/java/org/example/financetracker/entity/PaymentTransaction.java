package org.example.financetracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.financetracker.entity.enums.PaymentTransactionStatus;
import org.example.financetracker.entity.enums.convert.PaymentTransactionStatusConvert;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction extends BasicEntity {

     private String currency;
     private BigDecimal amount;

     @Convert(converter = PaymentTransactionStatusConvert.class)
     private PaymentTransactionStatus status;

     private String errorMessage;

     @ManyToOne
     @JoinColumn(name = "sourceBankAccountId")
     private BankAccount sourceBankAccount;

     @ManyToOne
     @JoinColumn(name = "destinationBankAccountId")
     private BankAccount destinationBankAccount;

     @OneToMany(mappedBy = "paymentTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<PaymentRefund> refunds;
}
