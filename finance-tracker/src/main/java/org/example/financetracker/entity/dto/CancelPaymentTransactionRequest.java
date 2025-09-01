package org.example.financetracker.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPaymentTransactionRequest {
    @NotNull(message = " transaction id is null")
    private Long transactionId;

    @NotNull
    @Min(value = 1, message = "Cancel amount is null")
    private BigDecimal refundedAmount;
    private String reasons;

}
