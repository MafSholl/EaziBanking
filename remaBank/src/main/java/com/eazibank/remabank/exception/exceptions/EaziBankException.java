package com.eazibank.remabank.exception.exceptions;

import lombok.Getter;

public class EaziBankException extends RuntimeException{

    @Getter
    private final int statusCode;

    public EaziBankException(String message) {
        super(message);
        this.statusCode = 500;
    }
    public EaziBankException(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
