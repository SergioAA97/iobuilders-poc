package com.picobankapi.poc.wallet.domain;

public class WalletNotFoundException extends WalletException {

    public WalletNotFoundException(Long id) {
        super(String.format("The walllet %s was not found", id));
    }

}
