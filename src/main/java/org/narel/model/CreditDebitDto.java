package org.narel.model;

import lombok.Data;
import org.narel.entity.Account;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Period;
import java.util.List;
import java.util.UUID;

@Data
public class CreditDebitDto {

    private UUID id;
    private String fullName;
    private Account account;
    private Currency currency;
    private Instant openingDate;
    private Period period;
    private Instant operationDate;
    private BigDecimal accountBalance;
    private List<OperationDto> userOperations;

}
