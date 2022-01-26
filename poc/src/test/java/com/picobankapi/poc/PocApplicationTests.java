package com.picobankapi.poc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.picobankapi.poc.user.application.UserServiceImpl;
import com.picobankapi.poc.user.application.port.out.UserRepository;
import com.picobankapi.poc.user.domain.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PocApplicationTests {

	@Mock
	UserRepository repoMock;

	@InjectMocks
	UserServiceImpl serviceImplMock;

	ArrayList<User> users = new ArrayList<User>();

	@BeforeEach
	public void init_each() {
		MockitoAnnotations.openMocks(this);
		this.users = new ArrayList<User>() {
			{
				add(new User("Juan", "12345", "ElJuancho"));
				add(new User("Ana", "67890", "Anita87"));
				add(new User("Obi Wan", "theForce", "NotTheWan"));
			}
		};
	}

	@Test
	public void getAllUsers() {
		when(repoMock.findAll()).thenReturn(users);
		assertEquals(users, serviceImplMock.getUsers());
	}

	@Test
	public void getByUsername() {
		String username = "ElJuancho";
		User testUser = new User("Juan", "12345", "ElJuancho");
		when(repoMock.findByUsername(username)).thenReturn(testUser);
		assertEquals(testUser, serviceImplMock.getUserByUsername(username));
	}

}
