package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class AccountDoesNotExistException extends EaziBankException {

    public AccountDoesNotExistException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
