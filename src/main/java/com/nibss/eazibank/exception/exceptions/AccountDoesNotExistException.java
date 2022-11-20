package com.nibss.eazibank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class AccountDoesNotExistException extends EaziBankExceptions {

    public AccountDoesNotExistException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
