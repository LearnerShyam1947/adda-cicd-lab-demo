package com.shyam.controllers;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shyam.exceptions.StudentAlreadyExistsException;
import com.shyam.exceptions.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException ex) {
        HashMap<String, Object> errorResponse = new HashMap<String, Object>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("status", HttpStatus.NOT_FOUND.name());
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<?> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        HashMap<String, Object> errorResponse = new HashMap<String, Object>();
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("status", HttpStatus.CONFLICT.name());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex) {
        HashMap<String, Object> errorResponse = new HashMap<String, Object>();
        errorResponse.put("error", "An unexpected error occurred");
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.name());
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
