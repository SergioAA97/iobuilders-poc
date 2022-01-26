package com.picobankapi.poc.wallet.application.port.in;

import java.util.List;

import com.picobankapi.poc.wallet.domain.WalletMovement;

public interface WalletMovementService {
    List<WalletMovement> movements(Long id);
}
