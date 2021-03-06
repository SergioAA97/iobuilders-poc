package com.picobankapi.poc.wallet.application;

import java.math.BigDecimal;
import java.util.Optional;

import com.picobankapi.poc.wallet.application.events.WalletMovementEventEmitter;
import com.picobankapi.poc.wallet.application.port.in.WalletService;
import com.picobankapi.poc.wallet.application.port.out.WalletRepository;
import com.picobankapi.poc.wallet.domain.Wallet;
import com.picobankapi.poc.wallet.domain.WalletMovementEvent;
import com.picobankapi.poc.wallet.domain.WalletNotEnoughFundsException;
import com.picobankapi.poc.wallet.domain.WalletNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    private final WalletMovementEventEmitter emitter;

    @Override
    public BigDecimal balance(Long id) throws WalletNotFoundException {
        Wallet wallet = repository.getById(id);
        if (wallet == null)
            throw new WalletNotFoundException(id);
        return wallet.getBalance();
    }

    @Override
    public void deposit(Long id, BigDecimal amnt) throws WalletNotFoundException {
        Optional<Wallet> opWallet = repository.findById(id);
        if (!opWallet.isPresent()) {
            throw new WalletNotFoundException(id);
        } else {
            Wallet wallet = opWallet.get();
            wallet.deposit(amnt);
            emitter.publish(new WalletMovementEvent(this, null, wallet, amnt));
            repository.save(wallet);
        }
    }

    @Override
    public void withdraw(Long id, BigDecimal amnt) throws WalletNotEnoughFundsException, WalletNotFoundException {
        Optional<Wallet> opWallet = repository.findById(id);
        if (!opWallet.isPresent()) {
            throw new WalletNotFoundException(id);
        } else {
            Wallet wallet = opWallet.get();
            boolean withdrawn = wallet.withdraw(amnt);
            if (withdrawn) {
                emitter.publish(new WalletMovementEvent(this, wallet, null, amnt));
                repository.save(wallet);
            } else {
                throw new WalletNotEnoughFundsException(id);
            }
        }
    }

    @Override
    public Wallet create(Wallet wallet) {
        return repository.save(wallet);
    }

    @Override
    public void transfer(Long srcId, Long tgtId, BigDecimal amnt)
            throws WalletNotEnoughFundsException, WalletNotFoundException {
        Wallet walletSrc = repository.getById(srcId);
        Wallet walletTgt = repository.getById(tgtId);
        if (walletSrc == null) {
            throw new WalletNotFoundException(srcId);
        }
        if (walletTgt == null) {
            throw new WalletNotFoundException(tgtId);
        }
        boolean withdrawn = walletSrc.withdraw(amnt);
        if (!withdrawn) {
            throw new WalletNotEnoughFundsException(srcId);
        }
        walletTgt.deposit(amnt);
        emitter.publish(new WalletMovementEvent(this, walletSrc, walletTgt, amnt));
        repository.save(walletSrc);
        repository.save(walletTgt);
    }

}
