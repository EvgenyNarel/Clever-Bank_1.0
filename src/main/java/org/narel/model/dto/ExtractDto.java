package org.narel.model.dto;

import lombok.Builder;
import org.narel.entity.enums.Currency;
import org.narel.model.Period;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
public class ExtractDto {

    private String bankName;
    private String ownerFullName;
    private String accountNumber;
    private Currency currency;
    private Instant openingDate;
    private Period period;
    private Instant extractDate;
    private BigDecimal balance;
    private List<OperationDto> operations;
}

