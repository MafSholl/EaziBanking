package com.eazibank.egobank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRecipientException extends EaziBankExceptions{

    public InvalidRecipientException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }
}
