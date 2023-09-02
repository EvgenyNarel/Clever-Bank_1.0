package org.narel.model.dto;

import lombok.Builder;
import org.narel.entity.MoneyStatement;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public class MoneyStatementDto {

    private String fullName;
    private String bankName;
    private String accountNumber;
    private Currency currency;
    private Instant openingDate;
    private BigDecimal amount;
    private BigDecimal income;
    private BigDecimal expense;

    public static MoneyStatementDto from(MoneyStatement moneyStatement) {
        return MoneyStatementDto.builder()
                .fullName(moneyStatement.getFullName())
                .bankName(moneyStatement.getBankName())
                .accountNumber(moneyStatement.getAccountNumber())
                .currency(moneyStatement.getCurrency())
                .openingDate(moneyStatement.getOpeningDate())
                .amount(moneyStatement.getAmount())
                .income(moneyStatement.getIncome())
                .expense(moneyStatement.getExpense())
                .build();
    }
}

