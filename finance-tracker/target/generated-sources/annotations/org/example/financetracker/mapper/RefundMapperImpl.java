package org.example.financetracker.mapper;

import javax.annotation.processing.Generated;
import org.example.financetracker.entity.PaymentRefund;
import org.example.financetracker.entity.dto.CancelPaymentTransactionRequest;
import org.example.financetracker.entity.dto.CancelPaymentTransactionResponse;
import org.example.financetracker.entity.enums.RefundTransactionStatus;
import org.example.financetracker.entity.enums.enumsForDto.CommandResultStatus;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-31T16:02:51+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class RefundMapperImpl implements RefundMapper {

    @Override
    public PaymentRefund toEntity(CancelPaymentTransactionRequest cancelPaymentTransactionRequest) {
        if ( cancelPaymentTransactionRequest == null ) {
            return null;
        }

        PaymentRefund paymentRefund = new PaymentRefund();

        paymentRefund.setRefundedAmount( cancelPaymentTransactionRequest.getRefundedAmount() );

        return paymentRefund;
    }

    @Override
    public CancelPaymentTransactionResponse toResponse(PaymentRefund paymentRefund) {
        if ( paymentRefund == null ) {
            return null;
        }

        CancelPaymentTransactionResponse cancelPaymentTransactionResponse = new CancelPaymentTransactionResponse();

        cancelPaymentTransactionResponse.setStatus( refundTransactionStatusToCommandResultStatus( paymentRefund.getStatus() ) );

        return cancelPaymentTransactionResponse;
    }

    protected CommandResultStatus refundTransactionStatusToCommandResultStatus(RefundTransactionStatus refundTransactionStatus) {
        if ( refundTransactionStatus == null ) {
            return null;
        }

        CommandResultStatus commandResultStatus;

        switch ( refundTransactionStatus ) {
            case SUCCESS: commandResultStatus = CommandResultStatus.SUCCESS;
            break;
            case FAILED: commandResultStatus = CommandResultStatus.FAILED;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + refundTransactionStatus );
        }

        return commandResultStatus;
    }
}
