package com.tripproject.exception;

import org.springframework.http.HttpStatus;

public class NoSuchUserException extends CalorieException{


    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
