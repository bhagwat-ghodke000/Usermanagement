package com.example.UserManagement.exception;

import lombok.Data;

@Data
public class SQLIntegrityConstraintViolationException extends RuntimeException {

    String fieldValue;

    public SQLIntegrityConstraintViolationException(String fieldValue) {
        super(String.format("Duplicate %s is not allowed", fieldValue));
        this.fieldValue = fieldValue;
    }
}
