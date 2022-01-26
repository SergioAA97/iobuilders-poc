package com.picobankapi.poc.user.domain;

public class UserAlreadyExistsException extends UserException {

    public UserAlreadyExistsException(String name) {
        super(String.format("User with name %s is already registered", name));
    }

}
