package com.example.liquibasedemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServiceGetEntityException extends Exception {

    public ServiceGetEntityException(String message) {
        super(message);
    }

}