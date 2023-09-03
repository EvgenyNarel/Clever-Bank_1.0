package org.narel.entity;

import lombok.Data;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class MoneyStatement {

    private String fullName;
    private String bankName;
    private String accountNumber;
    private Currency currency;
    private Instant openingDate;
    private BigDecimal amount;
    private BigDecimal income;
    private BigDecimal expense;
}