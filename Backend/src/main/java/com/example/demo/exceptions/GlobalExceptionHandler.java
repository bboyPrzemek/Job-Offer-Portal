package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserExistsException ex) {
        return ex.getMessage();
    }
    
    @ExceptionHandler(EmailServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String handleEmailServiceException(EmailServiceException ex) {
        return ex.getMessage();
    }
}