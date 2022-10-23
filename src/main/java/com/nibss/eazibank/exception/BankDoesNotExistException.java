package com.nibss.eazibank.exception;

import org.springframework.http.HttpStatus;

public class BankDoesNotExistException extends EaziBankExceptions {

    public BankDoesNotExistException(String message) {
        super(message, HttpStatus.NO_CONTENT.value());
    }
}
