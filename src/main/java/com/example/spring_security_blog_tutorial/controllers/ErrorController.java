package com.example.spring_security_blog_tutorial.controllers;

import com.example.spring_security_blog_tutorial.domain.dtos.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {

        ApiErrorResponse error = ApiErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("an expected error")
                .build();

        System.out.println(ex);


       return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
