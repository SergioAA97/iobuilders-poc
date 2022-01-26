package com.picobankapi.poc.wallet.application.port.out;

import com.picobankapi.poc.wallet.domain.WalletMovement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletMovementRepository extends JpaRepository<WalletMovement, Long> {

}
