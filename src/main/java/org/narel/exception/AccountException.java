package org.narel.exception;

public class AccountException extends RuntimeException {

    public static final AccountException INSUFFICIENT_FUNDS = new AccountException("Insufficient funds");

    public AccountException(String message){
        super(message);
    }
}
