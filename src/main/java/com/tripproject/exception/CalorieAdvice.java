package com.tripproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CalorieAdvice {

    @ExceptionHandler(NoSuchUserException.class)
    public ResponseEntity<ExceptionDto> noSuchUserException(NoSuchUserException e){
        return ResponseEntity.badRequest().body(new ExceptionDto(e.getMessage()));
    }







    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class ExceptionDto{
        private String message;
    }
}
