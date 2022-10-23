package com.nibss.eazibank.exception;

import org.springframework.http.HttpStatus;

public class AccountDoesNotExistException extends EaziBankExceptions {

    public AccountDoesNotExistException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
