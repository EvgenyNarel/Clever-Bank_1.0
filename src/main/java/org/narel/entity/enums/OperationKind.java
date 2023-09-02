package org.narel.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum OperationKind {
    REPLENISHMENT("Пополнение"),
    WITHDRAWAL("Снятие"),
    TRANSFER("Перевод");

    private final String kind;

}
