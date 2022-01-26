package com.picobankapi.poc.wallet.domain;

public class WalletNotEnoughFundsException extends WalletException {

    public WalletNotEnoughFundsException(Long id) {
        super(String.format("The wallet %s doesn't have enough funds", id));
    }
    
}
