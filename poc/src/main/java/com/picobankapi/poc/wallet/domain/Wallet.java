package com.picobankapi.poc.wallet.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.picobankapi.poc.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wallets")
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(name = "balance")
    @Getter
    private BigDecimal balance;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = com.picobankapi.poc.user.domain.User.class)
    private User user;

    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Wallet [id=" + id + ",balance=" + balance + "]";
    }

    public void deposit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount) {
        if (this.balance.compareTo(amount) < 0) {
            return false;
        }
        this.balance = balance.subtract(amount);
        return true;
    }
}
