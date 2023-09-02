package org.narel.entity;

import java.util.UUID;
/**
 *
 */
public interface Entity {
    void setId(UUID id);

    UUID getId();
}
