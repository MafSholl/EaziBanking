package com.nibss.eazibank.exception;

import org.springframework.http.HttpStatus;

public class InvalidLoginDetailException extends EaziBankExceptions{

    public InvalidLoginDetailException(String message) {
        super(message, HttpStatus.NO_CONTENT.value());
    }
}
