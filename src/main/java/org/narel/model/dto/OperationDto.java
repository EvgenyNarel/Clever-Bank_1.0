package org.narel.model.dto;

import lombok.Builder;
import lombok.Data;
import org.narel.entity.Account;
import org.narel.entity.Customer;
import org.narel.entity.Operation;
import org.narel.entity.enums.Currency;
import org.narel.entity.enums.OperationKind;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
public class OperationDto {

    private UUID id;
    private UUID senderId;
    private UUID recipientId;
    private Currency currency;
    private OperationKind operationKind;
    private BigDecimal amount;
    private Instant operationDate;

    public static List<OperationDto> from(List<Operation> operations) {
        return operations == null || operations.isEmpty()
                ? Collections.emptyList()
                : operations.stream()
                .map(OperationDto::from)
                .collect(Collectors.toList());
    }

    public static OperationDto from(Operation operation) {
        return OperationDto.builder()
                .id(operation.getId())
                .senderId(operation.getSenderId())
                .recipientId(operation.getRecipientId())
                .currency(operation.getCurrency())
                .operationKind(operation.getOperationKind())
                .amount(operation.getAmount())
                .operationDate(operation.getOperationDate())
                .build();
    }

}
