package org.narel.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public enum OperationKind {
    REPLENISHMENT("����������"),
    WITHDRAWAL("������"),
    ACCRUAL("���������� ���������"),
    TRANSFER("�������");

    private final String kind;

}
