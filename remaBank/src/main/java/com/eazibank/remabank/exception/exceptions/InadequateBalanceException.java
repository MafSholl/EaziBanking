package com.eazibank.remabank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InadequateBalanceException extends EaziBankException {
    public InadequateBalanceException(String message){
        super(message, HttpStatus.CONFLICT.value());
    }

}
