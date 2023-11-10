package com.hostfully.bookingmanager.controllers.exceptions;

import com.hostfully.bookingmanager.exceptions.StandardError;
import org.springframework.validation.FieldError;

import java.io.Serial;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidationError extends StandardError {
    @Serial
    private static final long serialVersionUID = 1L;

    private Map<String, String> errors = new HashMap<>();
    public Map<String, String> getErrors() {
        return errors;
    }

    private void addError(String fieldName, String message) {
        errors.put(fieldName, message);
    }

    public void addErrors(List<FieldError> errors) {
        errors.forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            this.addError(fieldName, errorMessage);
        });
    }

}