package com.nibss.eazibank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidLoginDetailException extends EaziBankExceptions{

    public InvalidLoginDetailException(String message) {
        super(message, HttpStatus.NO_CONTENT.value());
    }
}
