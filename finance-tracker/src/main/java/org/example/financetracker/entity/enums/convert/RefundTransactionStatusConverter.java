package org.example.financetracker.entity.enums.convert;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.financetracker.entity.enums.RefundTransactionStatus;

@Converter(autoApply = true)
public class RefundTransactionStatusConverter implements AttributeConverter<RefundTransactionStatus, String> {

    @Override
    public String convertToDatabaseColumn(RefundTransactionStatus refundTransactionStatus) {
        return refundTransactionStatus == null ? null : refundTransactionStatus.name();
    }

    @Override
    public RefundTransactionStatus convertToEntityAttribute(String s) {
        return s == null ? null : RefundTransactionStatus.RefundTransactionStatusFromString(s);
    }
}
