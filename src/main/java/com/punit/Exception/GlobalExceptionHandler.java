package com.punit.Exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "News Feed not found")
    private String message1;
    @Value(value = "User is already following this user")
    private String message2;
    @Value(value = "Create post failed")
    private String message3;

    @ExceptionHandler(value = NewsFeedNotFoundException.class)
    public ResponseEntity newsFeedNotFoundException(NewsFeedNotFoundException newsFeedNotFoundException) {
        return new ResponseEntity(message2, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>(message3, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}