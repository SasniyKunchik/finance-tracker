package org.example.financetracker.service.handler;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.controller.kafka.producer.PaymentTransactionProducer;
import org.example.financetracker.entity.BankAccount;
import org.example.financetracker.entity.dto.CreatePaymentTransactionRequest;
import org.example.financetracker.entity.enums.PaymentTransactionCommand;
import org.example.financetracker.entity.enums.PaymentTransactionStatus;
import org.example.financetracker.mapper.PaymentTransactionMapper;
import org.example.financetracker.service.BankAccountService;
import org.example.financetracker.service.PaymentTransactionService;
import org.example.financetracker.service.PaymentTransactionValidator;
import org.example.financetracker.util.JsonConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreatePaymentTransactionHandler implements PaymentTransactionCommandHandler {
    private final JsonConverter jsonConverter;
    private final PaymentTransactionValidator paymentTransactionValidator;
    private final BankAccountService bankAccountService;
    private final PaymentTransactionMapper paymentTransactionMapper;
    private final PaymentTransactionService paymentTransactionService;
    private final PaymentTransactionProducer paymentTransactionProducer;

    @Override
    @Transactional
    public void process(String requestId, String message) {
        var request = jsonConverter.toObject(message, CreatePaymentTransactionRequest.class);
        paymentTransactionValidator.validateCreatePaymentTransactionRequest(request);
        var sourceBankAccount = bankAccountService.findById(request.getSourceBankAccountId()).get();
        sourceBankAccount.setBalance(
                sourceBankAccount.getBalance().subtract(request.getAmount()) // BigDecimal
        );

        Optional<BankAccount> destinationBankAccount = Optional.empty();
        if (request.getDestinationBankAccountId() != null){
            destinationBankAccount = bankAccountService.findById(request.getDestinationBankAccountId());
            destinationBankAccount.get().setBalance(destinationBankAccount.get().getBalance().add(request.getAmount()));
        }
        var entity = paymentTransactionMapper.toEntity(request);
        entity.setSourceBankAccount(sourceBankAccount);
        destinationBankAccount.ifPresent(entity::setDestinationBankAccount);
        entity.setStatus(PaymentTransactionStatus.OK);

        var savedEntity = paymentTransactionService.save(entity);

        paymentTransactionProducer.sendCommandResult(
                PaymentTransactionProducer.RESULT_TOPIC,
                requestId,
                jsonConverter.toJson(savedEntity),
                PaymentTransactionCommand.CREATE
        );
    }

}
