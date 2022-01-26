package com.picobankapi.poc.user.domain;

public class UserNotFoundException extends UserException {
    
    public UserNotFoundException(String name) {
        super(String.format("The user %s was not found", name));
    }

}
