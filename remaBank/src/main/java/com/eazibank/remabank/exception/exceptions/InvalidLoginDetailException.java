package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidLoginDetailException extends EaziBankException {

    public InvalidLoginDetailException(String message) {
        super(message, HttpStatus.NO_CONTENT.value());
    }
}
