package org.example.financetracker.mapper;


import org.example.financetracker.entity.PaymentRefund;
import org.example.financetracker.entity.dto.CancelPaymentTransactionRequest;
import org.example.financetracker.entity.dto.CancelPaymentTransactionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefundMapper {
    PaymentRefund toEntity(CancelPaymentTransactionRequest cancelPaymentTransactionRequest);
    CancelPaymentTransactionResponse toResponse(PaymentRefund paymentRefund);
}
