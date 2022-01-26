package com.picobankapi.poc.user.application.events;

import com.picobankapi.poc.user.domain.UserRegisteredEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredEventEmitter {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(UserRegisteredEvent event) {
        publisher.publishEvent(event);
    }

}
