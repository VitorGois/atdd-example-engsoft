package com.engsoft.coursesapi.infraestructure.exceptions;

public class BadRequestException extends Exception {
    
    public BadRequestException(String message) {
        super(message);
    }

}
