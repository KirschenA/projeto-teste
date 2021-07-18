package com.escola.config;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ExceptionResponseWrapper {

    private HttpStatus status;
    private List<String> message;
    private List<String> errors;

    public ExceptionResponseWrapper(HttpStatus status, List<String> message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ExceptionResponseWrapper(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message =  Arrays.asList(message);
        errors = Arrays.asList(error);
    }
}