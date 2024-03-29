package com.eazibank.egobank.exception.handler;

import com.eazibank.egobank.transaction.controller.response.ApiResponse;
import com.eazibank.egobank.exception.exceptions.EaziBankExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> conflictHandler(EaziBankExceptions ex) {
        ApiResponse body = ApiResponse.builder()
                .status("failure")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(body, HttpStatus.valueOf(ex.getStatusCode()));
    }


}
