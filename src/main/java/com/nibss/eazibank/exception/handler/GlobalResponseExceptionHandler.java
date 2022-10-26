package com.nibss.eazibank.exception.handler;

import com.nibss.eazibank.controller.response.ApiResponse;
import com.nibss.eazibank.exception.EaziBankExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler
    public ResponseEntity<?> conflictHandler(EaziBankExceptions ex) {
        ApiResponse body = ApiResponse.builder()
                .status("failure")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(body, HttpStatus.valueOf(ex.getStatusCode()));
    }
}
