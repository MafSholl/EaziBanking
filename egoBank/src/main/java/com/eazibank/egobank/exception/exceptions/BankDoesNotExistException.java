package com.eazibank.egobank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class BankDoesNotExistException extends EaziBankExceptions {

    public BankDoesNotExistException(String message) {
        super(message, HttpStatus.NO_CONTENT.value());
    }
}
