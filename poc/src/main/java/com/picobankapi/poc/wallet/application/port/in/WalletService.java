package com.picobankapi.poc.wallet.application.port.in;

import java.math.BigDecimal;

import com.picobankapi.poc.wallet.domain.Wallet;

public interface WalletService {

    BigDecimal balance(Long id);

    void deposit(Long id, BigDecimal amnt);

    void withdraw(Long id, BigDecimal amnt);

    void transfer(Long srcId, Long tgtId, BigDecimal amnt);

    Wallet create(Wallet wallet);
}
