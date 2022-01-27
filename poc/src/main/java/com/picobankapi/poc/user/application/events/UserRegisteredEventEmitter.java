package com.picobankapi.poc.user.application.events;

import com.picobankapi.poc.user.domain.UserRegisteredEvent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegisteredEventEmitter {

    private final ApplicationEventPublisher publisher;

    public void publish(UserRegisteredEvent event) {
        publisher.publishEvent(event);
    }

}
