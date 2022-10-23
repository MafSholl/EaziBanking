package com.nibss.eazibank.exception;

import org.springframework.http.HttpStatus;

public class InvalidRecipientException extends EaziBankExceptions{

    public InvalidRecipientException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
