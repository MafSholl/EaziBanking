package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRecipientException extends EaziBankException {

    public InvalidRecipientException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
