package com.picobankapi.poc.wallet.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import com.picobankapi.poc.user.domain.User;
import com.picobankapi.poc.wallet.application.events.WalletMovementEventEmitter;
import com.picobankapi.poc.wallet.application.port.out.WalletRepository;
import com.picobankapi.poc.wallet.domain.Wallet;
import com.picobankapi.poc.wallet.domain.WalletMovementEvent;
import com.picobankapi.poc.wallet.domain.WalletNotEnoughFundsException;
import com.picobankapi.poc.wallet.domain.WalletNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WalletServiceTest {

	@Mock
	WalletRepository repoMock;

	@Mock
	WalletMovementEventEmitter eventEmitter;

	@InjectMocks
	WalletServiceImpl serviceImplMock;

	User user_1 = new User(1L, "Kylo Ren", "ILuvSnoke", "RepublicCrusher");
	Wallet wallet_1 = new Wallet(1L, BigDecimal.ZERO, user_1);

	User user_2 = new User(2L, "Pricess Leia", "IWontStand", "RebelPricess");
	Wallet wallet_2 = new Wallet(2L, BigDecimal.valueOf(1000), user_2);

	@BeforeEach
	public void init_each() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void getBalanceTest() {
		when(repoMock.getById(anyLong())).thenReturn(wallet_2);
		BigDecimal balance = serviceImplMock.balance(wallet_2.getId());
		assertEquals(wallet_2.getBalance(), balance);
	}

	@Test
	public void getBalanceInexistentTest() {
		when(repoMock.getById(anyLong())).thenReturn(null);
		assertThrows(WalletNotFoundException.class, () -> {
			serviceImplMock.balance(wallet_1.getId());
		});
	}

	@Test
	public void makeValidDeposit() {
		when(repoMock.getById(anyLong())).thenReturn(wallet_1);
		when(repoMock.save(any(Wallet.class))).thenReturn(wallet_1);
		serviceImplMock.deposit(1L, BigDecimal.valueOf(100));
		verify(eventEmitter).publish(any(WalletMovementEvent.class));
		verify(repoMock, times(1)).save(wallet_1);
	}

	@Test
	public void makeDepositOnInvalidWallet() {
		when(repoMock.getById(anyLong())).thenReturn(null);
		when(repoMock.save(any(Wallet.class))).thenReturn(wallet_1);
		assertThrows(WalletNotFoundException.class, () -> {
			serviceImplMock.deposit(1L, BigDecimal.valueOf(100));
		});
	}

	@Test
	public void makeValidWithdraw() {
		when(repoMock.getById(anyLong())).thenReturn(wallet_2);
		when(repoMock.save(any(Wallet.class))).thenReturn(wallet_2);
		serviceImplMock.withdraw(wallet_2.getId(), BigDecimal.valueOf(100));
		verify(eventEmitter).publish(any(WalletMovementEvent.class));
		verify(repoMock, times(1)).save(wallet_2);
	}

	@Test
	public void makeInvalidWithdraw() {
		when(repoMock.getById(anyLong())).thenReturn(wallet_1);
		assertThrows(WalletNotEnoughFundsException.class, () -> {
			serviceImplMock.withdraw(wallet_1.getId(), BigDecimal.valueOf(100));
		});
	}

	@Test
	public void makeWithdrawalWalletNotFound() {
		when(repoMock.getById(anyLong())).thenReturn(null);
		assertThrows(WalletNotFoundException.class, () -> {
			serviceImplMock.withdraw(wallet_1.getId(), BigDecimal.valueOf(100));
		});
	}

	@Test
	public void makeValidTransfer() {
		when(repoMock.getById(wallet_1.getId())).thenReturn(wallet_1);
		when(repoMock.getById(wallet_2.getId())).thenReturn(wallet_2);
		serviceImplMock.transfer(wallet_2.getId(), wallet_1.getId(), BigDecimal.valueOf(100));
		verify(repoMock).save(wallet_1);
		verify(repoMock).save(wallet_1);
		verify(eventEmitter).publish(any(WalletMovementEvent.class));
	}

	@Test
	public void makeNotEnoughFundsTransfer() {
		when(repoMock.getById(wallet_1.getId())).thenReturn(wallet_1);
		when(repoMock.getById(wallet_2.getId())).thenReturn(wallet_2);

		assertThrows(WalletNotEnoughFundsException.class, () -> {
			serviceImplMock.transfer(wallet_1.getId(), wallet_2.getId(), BigDecimal.valueOf(100));
		});
	}

	@Test
	public void makeTransferWalletNotFoundSource() {
		when(repoMock.getById(wallet_1.getId())).thenReturn(null);
		when(repoMock.getById(wallet_2.getId())).thenReturn(wallet_2);

		assertThrows(WalletNotFoundException.class, () -> {
			serviceImplMock.transfer(wallet_1.getId(), wallet_2.getId(), BigDecimal.valueOf(100));
		});
	}

	@Test
	public void makeTransferWalletNotFoundTarget() {
		when(repoMock.getById(wallet_1.getId())).thenReturn(wallet_1);
		when(repoMock.getById(wallet_2.getId())).thenReturn(null);

		assertThrows(WalletNotFoundException.class, () -> {
			serviceImplMock.transfer(wallet_1.getId(), wallet_2.getId(), BigDecimal.valueOf(100));
		});
	}

}
