package com.engsoft.atdd.infraestructure.exceptions;

public class EmailAlreadyExistsException extends Exception {
    
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
