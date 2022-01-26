package com.picobankapi.poc.wallet.application.events;

import com.picobankapi.poc.wallet.domain.WalletMovementEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class WalletMovementEventEmitter {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publish(WalletMovementEvent event) {
        publisher.publishEvent(event);
    }

}
