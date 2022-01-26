package com.picobankapi.poc.wallet.application;

import java.util.ArrayList;
import java.util.List;

import com.picobankapi.poc.wallet.application.port.in.WalletMovementService;
import com.picobankapi.poc.wallet.application.port.out.WalletMovementRepository;
import com.picobankapi.poc.wallet.domain.Wallet;
import com.picobankapi.poc.wallet.domain.WalletMovement;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletMovementServiceImpl implements WalletMovementService {

    private final WalletMovementRepository repository;

    @Override
    public List<WalletMovement> movements(Long id) {
        List<WalletMovement> movements = repository.findAll();
        List<WalletMovement> filteredMovements = new ArrayList<WalletMovement>();
        for (WalletMovement walletMovement : movements) {
            Wallet source = walletMovement.getSource();
            Wallet target = walletMovement.getTarget();
            boolean match = false;
            if (source != null) {
                match = Long.compare(source.getId(), id) == 0;
            }
            if (target != null) {
                match = Long.compare(target.getId(), id) == 0;
            }
            if (match) {
                filteredMovements.add(walletMovement);
            }
        }
        return filteredMovements;
    }

}
