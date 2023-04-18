package com.eazibank.remabank.exception.handler;

import com.eazibank.remabank.transaction.controller.response.ApiResponse;
import com.eazibank.remabank.exception.exceptions.EaziBankExceptions;
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
