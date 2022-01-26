package com.picobankapi.poc;

import com.picobankapi.poc.user.application.UserServiceImpl;
import com.picobankapi.poc.user.application.port.out.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserTests {

	@Mock
	UserRepository repoMock;

	@InjectMocks
	UserServiceImpl serviceImplMock;

	@BeforeEach
	public void init_each() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void exampleTest() {
		
	}

}
