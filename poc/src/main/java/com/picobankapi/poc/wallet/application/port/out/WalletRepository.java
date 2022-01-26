package com.picobankapi.poc.wallet.application.port.out;

import com.picobankapi.poc.wallet.domain.Wallet;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
