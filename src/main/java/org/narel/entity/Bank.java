package org.narel.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Bank implements Entity{

    private UUID id;
    private String bankName;

}
