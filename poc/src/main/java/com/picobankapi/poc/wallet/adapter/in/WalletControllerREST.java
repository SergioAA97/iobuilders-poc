package com.picobankapi.poc.wallet.adapter.in;

import java.math.BigDecimal;

import com.picobankapi.poc.wallet.application.WalletServiceImpl;
import com.picobankapi.poc.wallet.domain.Wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletControllerREST {

    @Autowired
    private WalletServiceImpl walletService;

    @PostMapping(value = "/{id}/deposit/{amnt}")
    public void deposit(@PathVariable Long id, @PathVariable BigDecimal amnt) {
        walletService.deposit(id, amnt);
    }

    @PostMapping(value = "/{id}/withdraw/{amnt}")
    public void withdraw(@PathVariable Long id, @PathVariable BigDecimal amnt) {
        walletService.withdraw(id, amnt);
    }

    @PostMapping(value = "/{id}/transfer/{amnt}/to/{tgt}")
    public void transfer(@PathVariable Long id, @PathVariable BigDecimal amnt, @PathVariable Long tgt) {
        walletService.transfer(id, tgt, amnt);
    }

    @GetMapping("/{id}")
    public BigDecimal getBalance(@PathVariable Long id) {
        return walletService.balance(id);
    }

    @PostMapping("/create")
    public Wallet create(@RequestBody Wallet wallet) {
        return walletService.create(wallet);
    }

}
