package com.example.UserManagement.exception;


import com.example.UserManagement.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GolablExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponse> DataIntegrityViolationExceptionHandler(SQLIntegrityConstraintViolationException ex){

        String message=ex.getMessage();
        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_GATEWAY);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> HandleMethodArgumentException(MethodArgumentNotValidException ex){

        Map<String,String> map= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{

            String message = error.getDefaultMessage();
            String fieldName = ((FieldError)error).getField();
            map.put(fieldName,message);
        });

        return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);

    }

}

