package com.picobankapi.poc;

import com.picobankapi.poc.wallet.application.WalletServiceImpl;
import com.picobankapi.poc.wallet.application.port.out.WalletRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WalletTests {

	@Mock
	WalletRepository repoMock;

	@InjectMocks
	WalletServiceImpl serviceImplMock;

	@BeforeEach
	public void init_each() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void test() {
	}

}
