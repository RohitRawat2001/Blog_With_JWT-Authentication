package com.springProject.FirstProject.exceptions;

import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springProject.FirstProject.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

// Handler for resouce not found exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    // Handler for validating Fields mean name ,Email , etc
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> mp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
          String fieldName =  ((FieldError)error).getField();
          String message = error.getDefaultMessage();
          mp.put(fieldName,message);
        });

        return new ResponseEntity<Map<String,String>>(mp,HttpStatus.BAD_REQUEST);
    }
}
