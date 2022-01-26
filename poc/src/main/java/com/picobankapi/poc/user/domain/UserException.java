package com.picobankapi.poc.user.domain;

import com.picobankapi.poc.core.domain.DomainException;

public class UserException extends DomainException {

    public UserException(String message) {
        super(message);
    }

}
