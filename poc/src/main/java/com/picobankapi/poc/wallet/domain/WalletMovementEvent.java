package com.picobankapi.poc.wallet.domain;

import java.math.BigDecimal;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class WalletMovementEvent extends ApplicationEvent {

    @Getter
    private WalletMovement movmement;

    public WalletMovementEvent(Object src, Wallet source, Wallet target, BigDecimal amount) {
        super(src);
        this.movmement = new WalletMovement(source, target, amount);
    }

}
