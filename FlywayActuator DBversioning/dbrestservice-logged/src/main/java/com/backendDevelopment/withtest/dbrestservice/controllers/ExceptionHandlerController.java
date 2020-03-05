package com.backendDevelopment.withtest.dbrestservice.controllers;

import com.backendDevelopment.withtest.dbrestservice.throwable.APIExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.*;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody

public class ExceptionHandlerController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public APIExceptionHandler handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = fieldError.getDefaultMessage();
        APIExceptionHandler customError = new APIExceptionHandler("VALIDATION_FAILED", defaultMessage);
        return customError;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public APIExceptionHandler handleValidationError(ConstraintViolationException ex) {
        var constraintValidation = ex.getConstraintViolations();
        String defaultMessage = constraintValidation.iterator().next().getMessage();
        APIExceptionHandler customError = new APIExceptionHandler("VALIDATION_FAILED", defaultMessage);
        return customError;
    }
}
