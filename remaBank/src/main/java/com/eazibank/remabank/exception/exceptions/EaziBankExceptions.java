package com.eazibank.remabank.exception.exceptions;

import lombok.Getter;

public class EaziBankExceptions extends RuntimeException{

    @Getter
    private final int statusCode;
    public EaziBankExceptions(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}
