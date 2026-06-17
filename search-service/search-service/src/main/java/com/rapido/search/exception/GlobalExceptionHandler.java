package com.rapido.search.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ConstraintViolationException.class
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validationError(
            ConstraintViolationException ex) {

        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleError(
            Exception ex) {

        return ex.getMessage();
    }
}