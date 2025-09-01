package org.example.financetracker.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.financetracker.entity.PaymentTransaction;
import org.example.financetracker.entity.dto.CancelPaymentTransactionRequest;
import org.example.financetracker.entity.dto.CancelPaymentTransactionResponse;
import org.example.financetracker.entity.enums.RefundTransactionStatus;
import org.example.financetracker.mapper.RefundMapper;
import org.example.financetracker.repository.PaymentRefundRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefundService {
    private final PaymentRefundRepository refundRepository;
    private final RefundMapper refundMapper;

    public CancelPaymentTransactionResponse createRefund(CancelPaymentTransactionRequest request,
                                                                     PaymentTransaction paymentTransaction) {


        var entity = refundMapper.toEntity(request);
        entity.setPaymentTransaction(paymentTransaction);
        entity.setStatus(RefundTransactionStatus.SUCCESS);

        var savedEntity = refundRepository.save(entity);

        savedEntity.setPaymentTransaction(paymentTransaction);
        return refundMapper.toResponse(savedEntity);
    }
}
