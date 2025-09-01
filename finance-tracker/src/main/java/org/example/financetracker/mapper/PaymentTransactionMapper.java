package org.example.financetracker.mapper;

import org.example.financetracker.entity.PaymentTransaction;
import org.example.financetracker.entity.dto.CreatePaymentTransactionRequest;
import org.example.financetracker.entity.dto.CreatePaymentTransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentTransactionMapper {
    PaymentTransaction toEntity(CreatePaymentTransactionRequest request);
    CreatePaymentTransactionResponse toResponse(PaymentTransaction paymentTransaction);
}
