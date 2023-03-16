package com.tripproject.exception;

import org.springframework.http.HttpStatus;

public abstract class CalorieException extends RuntimeException{
    public CalorieException() {
    }

    public CalorieException(String message) {
        super(message);
    }

    public abstract  HttpStatus status();
}
