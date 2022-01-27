package com.picobankapi.poc.user.domain;

public class UserInvalidException extends UserException {

    public UserInvalidException() {
        super("User with invalid parameters");
    }

}
