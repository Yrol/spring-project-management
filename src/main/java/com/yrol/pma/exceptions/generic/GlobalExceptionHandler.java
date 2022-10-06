package com.yrol.pma.exceptions.generic;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNoRecordFoundException(NoRecordFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("No Record Found.");
        return errorResponse;
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorResponse handleInvalidEmailException(InvalidEmailException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid Email. Email address must be unique.");
        return errorResponse;
    }


}
