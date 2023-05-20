package com.engsoft.atdd.infraestructure.exceptions;

public class UserAlreadyExistsException extends Exception {
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
