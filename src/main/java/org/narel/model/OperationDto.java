package org.narel.model;

import lombok.Data;
import org.narel.entity.Account;
import org.narel.entity.Customer;
import org.narel.entity.enums.OperationKind;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data

public class OperationDto {

    private UUID id;
    private Customer sender;
    private Customer recipient;
    private OperationKind operationKind;
    private BigDecimal amount;
    private Instant operationDate;
    private Account accountSender;
    private Account accountRecipient;

}
