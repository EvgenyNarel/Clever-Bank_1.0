package org.narel.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data


public class Customer {

    private UUID id;
    private String fullName;
    private List<Account> accounts;

}
