package com.picobankapi.poc.wallet.adapter.in;

import java.util.List;

import com.picobankapi.poc.wallet.application.port.in.WalletMovementService;
import com.picobankapi.poc.wallet.domain.WalletMovement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movements")
public class WalletMovementControllerREST {

    @Autowired
    private WalletMovementService walletMovementService;

    @GetMapping("/{id}")
    public List<WalletMovement> getMovements(@PathVariable Long id) {
        return walletMovementService.movements(id);
    }

}
