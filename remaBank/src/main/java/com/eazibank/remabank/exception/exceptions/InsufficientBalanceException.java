package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends EaziBankException {

    public InsufficientBalanceException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
