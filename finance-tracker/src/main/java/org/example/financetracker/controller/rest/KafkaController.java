package org.example.financetracker.controller.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.controller.kafka.producer.PaymentTransactionProducer;
import org.example.financetracker.entity.PaymentTransaction;
import org.example.financetracker.entity.dto.CancelPaymentTransactionRequest;
import org.example.financetracker.entity.dto.CreatePaymentTransactionRequest;
import org.example.financetracker.entity.enums.PaymentTransactionCommand;
import org.example.financetracker.mapper.PaymentTransactionMapper;
import org.example.financetracker.util.JsonConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api/start")
@RequiredArgsConstructor
public class KafkaController {
    private final PaymentTransactionProducer producer;
    private final JsonConverter jsonConverter;

    @PostMapping("/create-payment")
    public String createPayment(){
        var requestId = "request-id";

        CreatePaymentTransactionRequest request = new CreatePaymentTransactionRequest(1L, 2L, new BigDecimal("200.00"), "USD", "Test Payment");
        producer.sendCommandResult(PaymentTransactionProducer.RESULT_TOPIC,
                requestId, jsonConverter.toJson(request), PaymentTransactionCommand.CREATE);
        return "create payment command sent with request id " + requestId;
    }

    @PostMapping("/cancel-payment")
    public String cancelPayment(){
        var requestId = "request-id";

        CancelPaymentTransactionRequest request = new CancelPaymentTransactionRequest(1L, new BigDecimal("100.00"), "Test Refund");

        producer.sendCommandResult(PaymentTransactionProducer.RESULT_TOPIC, requestId, jsonConverter.toJson(request),
                PaymentTransactionCommand.REFUND);

        return "cancel payment command sent with request id " + requestId;
    }
}
