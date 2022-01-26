package com.picobankapi.poc.core.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid arguments.")
public class InvalidArgumentsException extends DomainException {

    public InvalidArgumentsException(String message) {
        super(message);
    }
    
}
