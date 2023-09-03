package org.narel.exception;

import org.narel.entity.Entity;

public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE_PATTERN = "%s not found in DB";

    public <T extends Entity> EntityNotFoundException(Class<T> clazz) {
        super(String.format(MESSAGE_PATTERN, clazz.getName()));
    }
}