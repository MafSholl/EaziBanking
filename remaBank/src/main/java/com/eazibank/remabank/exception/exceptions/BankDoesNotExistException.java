package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class BankDoesNotExistException extends EaziBankException {

    public BankDoesNotExistException(String message) {
        super(message, HttpStatus.NO_CONTENT.value());
    }
}
