package com.picobankapi.poc.wallet.application.events;

import com.picobankapi.poc.wallet.application.port.out.WalletMovementRepository;
import com.picobankapi.poc.wallet.domain.WalletMovement;
import com.picobankapi.poc.wallet.domain.WalletMovementEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WalletMovementEventHandler implements ApplicationListener<WalletMovementEvent> {

    @Autowired
    WalletMovementRepository repository;

    @Override
    public void onApplicationEvent(WalletMovementEvent event) {
        WalletMovement movement = event.getMovmement();
        repository.save(movement);
    }

}
