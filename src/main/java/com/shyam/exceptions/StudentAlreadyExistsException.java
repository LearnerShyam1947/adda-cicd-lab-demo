package com.shyam.exceptions;

public class StudentAlreadyExistsException extends RuntimeException {
    
    public StudentAlreadyExistsException(String message) {
        super(message);
    }

    public StudentAlreadyExistsException(String name, String email) {
        super("Student already exists with name: " + name + " or email: " + email);
    }
}
