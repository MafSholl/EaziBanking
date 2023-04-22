package com.eazibank.egobank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InsufficientBalanceException extends EaziBankExceptions {

    public InsufficientBalanceException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
