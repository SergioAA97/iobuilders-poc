package com.picobankapi.poc.wallet.adapter.in;

import java.math.BigDecimal;

import com.picobankapi.poc.wallet.adapter.DepositWithdrawalRequestBody;
import com.picobankapi.poc.wallet.adapter.TransferRequestBody;
import com.picobankapi.poc.wallet.application.WalletServiceImpl;
import com.picobankapi.poc.wallet.domain.WalletException;
import com.picobankapi.poc.wallet.domain.WalletNotEnoughFundsException;
import com.picobankapi.poc.wallet.domain.WalletNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletControllerREST {

    private final WalletServiceImpl walletService;

    @PostMapping(value = "/{id}/deposit")
    public void deposit(@PathVariable Long id, @RequestBody DepositWithdrawalRequestBody body) throws WalletNotFoundException {
        walletService.deposit(id, body.getAmount());
    }

    @PostMapping(value = "/{id}/withdraw")
    public void withdraw(@PathVariable Long id, @RequestBody DepositWithdrawalRequestBody body) throws WalletNotEnoughFundsException, WalletNotFoundException {
        walletService.withdraw(id, body.getAmount());
    }

    @PostMapping(value = "/transfer")
    public void transfer(@RequestBody TransferRequestBody transferBody) throws WalletException {
        walletService.transfer(transferBody.getSourceId(), transferBody.getTargetId(), transferBody.getAmount());
    }

    @GetMapping("/{id}")
    public BigDecimal getBalance(@PathVariable Long id) throws WalletException {
        return walletService.balance(id);
    }

}
