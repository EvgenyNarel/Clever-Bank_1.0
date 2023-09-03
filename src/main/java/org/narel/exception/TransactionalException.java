package org.narel.exception;

public class TransactionalException extends RuntimeException {

    public TransactionalException(Throwable cause) {
        super(cause);
    }
}
