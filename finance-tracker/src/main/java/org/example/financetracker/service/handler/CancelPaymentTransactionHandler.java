package org.example.financetracker.service.handler;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.controller.kafka.producer.PaymentTransactionProducer;
import org.example.financetracker.entity.dto.CancelPaymentTransactionRequest;
import org.example.financetracker.entity.enums.PaymentTransactionCommand;
import org.example.financetracker.mapper.PaymentTransactionMapper;
import org.example.financetracker.service.BankAccountService;
import org.example.financetracker.service.PaymentTransactionService;
import org.example.financetracker.service.PaymentTransactionValidator;
import org.example.financetracker.service.RefundService;
import org.example.financetracker.util.JsonConverter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CancelPaymentTransactionHandler implements PaymentTransactionCommandHandler {
    private JsonConverter jsonConverter;
    private final PaymentTransactionValidator paymentTransactionValidator;
    private final PaymentTransactionService paymentTransactionService;
    private final BankAccountService bankAccountService;
    private final RefundService refundService;
    private final PaymentTransactionProducer paymentTransactionProducer;

    @Override
    @Transactional
    public void process(String requestId, String message) {
        var request = jsonConverter.toObject(message, CancelPaymentTransactionRequest.class);
        paymentTransactionValidator.validateCancelPaymentTransactionRequest(request);

        var sourceTransaction = paymentTransactionService.findById(request.getTransactionId()).get();
        var sourceBankAccount = sourceTransaction.getSourceBankAccount();

        sourceBankAccount.setBalance(sourceBankAccount.getBalance().add(request.getRefundedAmount()));

        if(sourceTransaction.getDestinationBankAccount() != null) {
            var destinationBankAccount = sourceTransaction.getDestinationBankAccount();
            destinationBankAccount.setBalance(destinationBankAccount.getBalance().add(request.getRefundedAmount()));
        }

        var response = refundService.createRefund(request, sourceTransaction);

        paymentTransactionProducer.sendCommandResult(PaymentTransactionProducer.RESULT_TOPIC,
                requestId, jsonConverter.toJson(response), PaymentTransactionCommand.REFUND);
    }
}
