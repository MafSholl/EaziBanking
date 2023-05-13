package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class TransactionErrorException extends EaziBankException {
    public TransactionErrorException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
