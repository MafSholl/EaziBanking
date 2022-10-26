package com.nibss.eazibank.exception;

public class EaziBankExceptions extends RuntimeException{

    private final int statusCode;
    public EaziBankExceptions(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
