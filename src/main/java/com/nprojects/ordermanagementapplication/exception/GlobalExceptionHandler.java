package com.nprojects.ordermanagementapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyRegistered(EmailAlreadyRegisteredException exception){

        Map<String, String> body=Map.of("error",exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String,String>> handleBadCredentials(BadCredentialsException exception){

        Map<String, String> body=Map.of("error","Invalid email or password");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }
}
