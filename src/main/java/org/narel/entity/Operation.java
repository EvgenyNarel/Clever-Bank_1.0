package org.narel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.narel.entity.enums.Currency;
import org.narel.entity.enums.OperationKind;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Operation implements Entity {

    private UUID id;
    private UUID senderId;
    private UUID recipientId;
    private Currency currency;
    private OperationKind kind;
    private BigDecimal amount;
    private Instant operationDate;
}
