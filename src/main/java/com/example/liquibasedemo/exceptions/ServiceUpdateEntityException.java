package com.example.liquibasedemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class ServiceUpdateEntityException extends Exception {

    public ServiceUpdateEntityException(String message) {
        super(message);
    }

}