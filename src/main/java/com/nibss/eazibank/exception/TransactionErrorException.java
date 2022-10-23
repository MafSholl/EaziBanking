package com.nibss.eazibank.exception;

import org.springframework.http.HttpStatus;

public class TransactionErrorException extends EaziBankExceptions {
    public TransactionErrorException(String message) {
        super(message, HttpStatus.CONFLICT.value());
    }
}
