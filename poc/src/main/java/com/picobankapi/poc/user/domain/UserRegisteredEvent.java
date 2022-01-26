package com.picobankapi.poc.user.domain;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class UserRegisteredEvent extends ApplicationEvent {

    @Getter
    private User user;

    public UserRegisteredEvent(Object src, User newUser) {
        super(src);
        this.user = newUser;
    }

    @Override
    public String toString() {
        return this.user.toString();
    }

}
