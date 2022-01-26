package com.picobankapi.poc.wallet.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movements")
@NoArgsConstructor
public class WalletMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.EAGER)
    private Wallet source;

    @Getter
    @ManyToOne(targetEntity = Wallet.class, fetch = FetchType.EAGER)
    private Wallet target;

    @Getter
    private BigDecimal amount;

    public enum WalletMovementType {
        WITHDRAW,
        DEPOSIT,
        TRANSFER,
        OTHER
    }

    @Getter
    private WalletMovementType type;

    public WalletMovement(Wallet source, Wallet target, BigDecimal amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
        this.type = this.calculateType();
    }

    public WalletMovementType calculateType() {
        if (this.source == null && this.target == null)
            return WalletMovementType.OTHER;
        if (this.source == null)
            return WalletMovementType.DEPOSIT;
        if (this.target == null)
            return WalletMovementType.WITHDRAW;
        return WalletMovementType.TRANSFER;
    }

}
