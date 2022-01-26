package com.picobankapi.poc.wallet.domain;

import com.picobankapi.poc.core.domain.DomainException;

public class WalletException extends DomainException {
    public WalletException(String message) {
        super(message);
    }
}
