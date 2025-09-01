package org.example.financetracker.service;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.entity.BankAccount;
import org.example.financetracker.entity.PaymentRefund;
import org.example.financetracker.entity.dto.CancelPaymentTransactionRequest;
import org.example.financetracker.entity.dto.CreatePaymentTransactionRequest;
import org.example.financetracker.errors.PaymentTransactionValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTransactionValidator {
    private final Validator validator;
    private final BankAccountService bankAccountService;
    private final PaymentTransactionService paymentTransactionService;

    public void validateCreatePaymentTransactionRequest(CreatePaymentTransactionRequest request) {
        var violations = validator.validate(request);
        List<String> errors = new ArrayList<>(
                violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .toList());
        Optional<BankAccount> sourceBankAccount = Optional.empty();
        if (request.getSourceBankAccountId() != null) {
            sourceBankAccount = bankAccountService.findById(request.getSourceBankAccountId());
            if (sourceBankAccount.isEmpty()) {
                errors.add("Source bank account not found, id: " + request.getSourceBankAccountId());
            }

        }
        Optional<BankAccount> destinationBankAccount;
        if (request.getDestinationBankAccountId() != null) {
            destinationBankAccount = bankAccountService.findById(request.getDestinationBankAccountId());
            if (destinationBankAccount.isEmpty()) {
                errors.add("Destination bank account not found, id: " + request.getDestinationBankAccountId());

            }
        }

        if (request.getAmount() != null && sourceBankAccount.isPresent()) {
            if (sourceBankAccount.get().getBalance().compareTo(request.getAmount()) < 0) {
                errors.add("Source bank account balance less than request amount, id: " + request.getSourceBankAccountId());
            }
        }

        if (!errors.isEmpty()) {
            throw new PaymentTransactionValidationException(errors);
        }
    }

    public void validateCancelPaymentTransactionRequest(CancelPaymentTransactionRequest request) {
        List<String> errors = new ArrayList<>(validator.validate(request).stream().map(ConstraintViolation::getMessage).toList());


        if (request.getTransactionId()!= null) {
            var sourceTransaction = paymentTransactionService.findById(request.getTransactionId());
            if(sourceTransaction.isEmpty()) {
                errors.add("Transaction not found, id: " + request.getTransactionId());
            } else{
                var existedSourceTransaction = sourceTransaction.get();
                var refundedAmount = sourceTransaction.get().getRefunds().stream().map(PaymentRefund::getRefundedAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
                if(existedSourceTransaction.getAmount().subtract(refundedAmount).compareTo((request.getRefundedAmount())) < 0) {
                    errors.add("Source amount less than request amount for refund, id: " + request.getTransactionId());
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new PaymentTransactionValidationException(errors);
        }

    }
}
