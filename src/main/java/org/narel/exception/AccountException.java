package org.narel.exception;

public class AccountException extends RuntimeException {

    private final String message;
    public AccountException(String message){
        this.message = message;
    }
}
