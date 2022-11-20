package com.nibss.eazibank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class TransactionErrorException extends EaziBankExceptions {
    public TransactionErrorException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
