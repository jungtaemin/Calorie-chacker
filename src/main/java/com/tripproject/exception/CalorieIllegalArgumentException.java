package com.tripproject.exception;

import org.springframework.http.HttpStatus;

public class CalorieIllegalArgumentException extends CalorieException{


    public CalorieIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
