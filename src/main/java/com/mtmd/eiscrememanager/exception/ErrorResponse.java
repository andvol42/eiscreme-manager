package com.mtmd.eiscrememanager.exception;

import java.util.ArrayList;
import java.util.List;


public class ErrorResponse {
    private final int status;
    private final String message;
    private List<ValidationError> errors = new ArrayList<>();

    public ErrorResponse(int status, String message) {
        System.out.println(status);
        System.out.println(message);
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    private static class ValidationError{
        private final String field;
        private final String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public String getMessage() {
            return message;
        }
    }

    public void addValidationError(String field, String message){
        errors.add(new ValidationError(field, message));
    }

}


