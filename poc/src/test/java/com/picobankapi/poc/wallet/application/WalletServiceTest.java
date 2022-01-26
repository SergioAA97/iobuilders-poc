package com.picobankapi.poc.wallet.application;

import com.picobankapi.poc.wallet.application.port.out.WalletRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WalletServiceTest {

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
