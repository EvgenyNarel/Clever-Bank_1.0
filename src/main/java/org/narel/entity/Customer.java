package org.narel.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
public class Customer implements Entity {

    private UUID id;
    private String fullName;
}
