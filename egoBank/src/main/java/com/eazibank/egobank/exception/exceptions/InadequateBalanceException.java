package com.eazibank.egobank.exception.exceptions;

import org.springframework.http.HttpStatus;

public class InadequateBalanceException extends EaziBankExceptions{
    public InadequateBalanceException(String message){
        super(message, HttpStatus.CONFLICT.value());
    }

}
