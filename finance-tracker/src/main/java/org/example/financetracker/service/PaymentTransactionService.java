package org.example.financetracker.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.entity.PaymentTransaction;
import org.example.financetracker.entity.dto.CreatePaymentTransactionRequest;
import org.example.financetracker.entity.dto.CreatePaymentTransactionResponse;
import org.example.financetracker.mapper.PaymentTransactionMapper;
import org.example.financetracker.repository.PaymentTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentTransactionService {
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final PaymentTransactionMapper paymentTransactionMapper;

    public PaymentTransaction save(PaymentTransaction paymentTransaction){
        return paymentTransactionRepository.save(paymentTransaction);

    }

    public Optional<PaymentTransaction> findById(Long id){
        return paymentTransactionRepository.findById(id);
    }

}
