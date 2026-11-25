package com.mindfire.banking.exception;

import java.io.Serial;

public class BankException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BankException(String message) {
        super(message);
    }
}