package org.example.financetracker.entity.enums.convert;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.financetracker.entity.enums.PaymentTransactionStatus;

@Converter(autoApply = true)
public class PaymentTransactionStatusConvert implements AttributeConverter<PaymentTransactionStatus, String> {


    @Override
    public String convertToDatabaseColumn(PaymentTransactionStatus paymentTransactionStatus) {
        return paymentTransactionStatus == null ? null : paymentTransactionStatus.name();
    }

    @Override
    public PaymentTransactionStatus convertToEntityAttribute(String s) {
        return s == null ? null : PaymentTransactionStatus.getTransactionStatusFromString(s);
    }
}
