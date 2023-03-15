package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class CustomerAlreadyExistException extends EaziBankExceptions{
    public CustomerAlreadyExistException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE.value());
    }
}
