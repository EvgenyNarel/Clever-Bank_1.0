package org.narel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Bank {

    private UUID id;
    private String bankname;

}
