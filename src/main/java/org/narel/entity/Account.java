package org.narel.entity;

import lombok.Data;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class Account implements Entity {

    private UUID id;
    private String accountNumber;
    private UUID bankId;
    private UUID ownerId;
    private Currency currency;
    private BigDecimal amount;
    private Instant openingDate;
}
