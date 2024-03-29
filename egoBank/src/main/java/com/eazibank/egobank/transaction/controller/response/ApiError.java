package com.eazibank.egobank.transaction.controller.response;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }
    public ApiError(HttpServletRequest request) {
        this();
        this.path = request.getServletPath();
    }
}
