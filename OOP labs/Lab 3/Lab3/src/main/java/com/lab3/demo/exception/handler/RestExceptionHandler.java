package com.lab3.demo.exception.handler;

import com.lab3.demo.dto.ErrorInfo;
import com.lab3.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            CardNotFoundException.class,
            UserNotFound.class
    })
    @ResponseBody
    public ErrorInfo notFoundExceptionHandler(Exception e) {
        return new ErrorInfo().setTimestamp(System.currentTimeMillis())
                .setMessage(e.getMessage())
                .setDeveloperMessage(e.toString());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            CardIsBlocked.class,
            CardAlreadyExists.class,
            CardNumberNotNullException.class,
            CardStateException.class,
            UserAlreadyExistsException.class,
            OutOfAccountBalance.class
    })
    @ResponseBody
    public ErrorInfo badRequestExceptionHandler(Exception e) {
        return new ErrorInfo().setTimestamp(System.currentTimeMillis())
                .setMessage(e.getMessage())
                .setDeveloperMessage(e.toString());
    }
}

