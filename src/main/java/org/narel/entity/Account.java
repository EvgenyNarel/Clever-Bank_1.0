package org.narel.entity;

import lombok.Data;
import org.narel.entity.enums.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class Account {

    private UUID id;
    private String accountNumber;
    private Bank bank;
    private Customer owner;
    private Currency currency;
    private BigDecimal amount;
    private Instant openingDate;

}
