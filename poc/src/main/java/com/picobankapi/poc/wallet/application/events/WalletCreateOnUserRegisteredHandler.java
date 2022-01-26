package com.picobankapi.poc.wallet.application.events;

import java.math.BigDecimal;

import com.picobankapi.poc.user.domain.UserRegisteredEvent;
import com.picobankapi.poc.wallet.application.port.in.WalletService;
import com.picobankapi.poc.wallet.domain.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WalletCreateOnUserRegisteredHandler implements ApplicationListener<UserRegisteredEvent> {

    @Autowired
    WalletService service;

    @Override
    public void onApplicationEvent(UserRegisteredEvent event) {
        service.create(new Wallet(BigDecimal.ZERO, event.getUser()));
    }

}
