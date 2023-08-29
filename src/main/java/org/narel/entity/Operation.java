package org.narel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.narel.entity.enums.OperationKind;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Operation {

  private UUID id;
  private Customer sender;
  private Customer recipient;
  private OperationKind operationKind;
  private BigDecimal amount;
  private Instant operationDate;


}
