package com.shyam.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shyam.dto.ApiErrorDTO;
import com.shyam.exceptions.StudentAlreadyExistsException;
import com.shyam.exceptions.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleStudentNotFoundException(StudentNotFoundException ex) {
        ApiErrorDTO errorResponse = new ApiErrorDTO();
        errorResponse.setError(ex.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.name());
        
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ApiErrorDTO> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        ApiErrorDTO errorResponse = new ApiErrorDTO();
        errorResponse.setError(ex.getMessage());
        errorResponse.setStatus(HttpStatus.CONFLICT.name());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDTO> handleGlobalException(Exception ex) {
        ApiErrorDTO errorResponse = new ApiErrorDTO();
        errorResponse.setError("An unexpected error occurred");
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }
}
