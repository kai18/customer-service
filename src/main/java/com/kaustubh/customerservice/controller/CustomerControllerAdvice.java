package com.kaustubh.customerservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomerControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException errors) {
        return ResponseEntity.badRequest().body(String.join(",",
                errors.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleValidationErrors(Exception e) {
        log.error("An error occurred while processing the request", e);
        return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
    }
}
