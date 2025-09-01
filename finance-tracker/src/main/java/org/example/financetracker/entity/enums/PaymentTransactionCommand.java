package org.example.financetracker.entity.enums;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public enum PaymentTransactionCommand {
    CREATE,
    REFUND,
    UNKNOWN;

    public static PaymentTransactionCommand fromString(String string) {
        try{
            return PaymentTransactionCommand.valueOf(string);
        }catch(IllegalArgumentException e){
            log.error(e.getMessage());
            return UNKNOWN;
        }
    }
}
