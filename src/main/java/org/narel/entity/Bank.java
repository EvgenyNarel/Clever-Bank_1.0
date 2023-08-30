package org.narel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class Bank {

    private UUID id;
    private String bankName;

}
