package com.picobankapi.poc.user.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.picobankapi.poc.core.domain.InvalidArgumentsException;
import com.picobankapi.poc.user.application.events.UserRegisteredEventEmitter;
import com.picobankapi.poc.user.application.port.out.UserRepository;
import com.picobankapi.poc.user.domain.User;
import com.picobankapi.poc.user.domain.UserAlreadyExistsException;
import com.picobankapi.poc.user.domain.UserInvalidException;
import com.picobankapi.poc.user.domain.UserNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

	@Mock
	UserRepository repoMock;

	@Mock
	UserRegisteredEventEmitter emitterMock;

	@InjectMocks
	UserServiceImpl serviceImplMock;

	@BeforeEach
	public void init_each() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void registerValidUser() {
		User user = new User("Anakin", "Padme123", "TatooineBoy");
		when(repoMock.save(any(User.class))).thenReturn(user);
		User newUser = serviceImplMock.registerUser(user);
		assertEquals(user, newUser);
	}

	@Test
	public void registerEmptyUserThrows() {
		User user = null;
		when(repoMock.save(any(User.class))).thenThrow(InvalidArgumentsException.class);
		assertThrows(InvalidArgumentsException.class, () -> {
			serviceImplMock.registerUser(user);
		});
	}

	@Test
	public void registerInvalidUserThrows() {
		User user = new User(null, null, "TatooineBoy");
		when(repoMock.save(any(User.class))).thenThrow(UserInvalidException.class);
		assertThrows(UserInvalidException.class, () -> {
			serviceImplMock.registerUser(user);
		});
	}

	@Test
	public void registeExistingUserThrows() {
		User user = new User(1L, "Anakin", "Padme123", "TatooineBoy");
		User existingUser = new User(2L, "Anakin", "Padme321", "TatooineBoy1992");
		
		when(repoMock.save(any(User.class))).thenReturn(user);
		when(repoMock.findByUsername(user.getUsername())).thenReturn(null);
		
		assertEquals(serviceImplMock.registerUser(user),user);
		
		when(repoMock.findByUsername(existingUser.getUsername())).thenReturn(user);
		
		assertThrows(UserAlreadyExistsException.class, () -> {
			serviceImplMock.registerUser(existingUser);
		});
	}

	@Test
	public void getUserByUsername() {
		User user = new User(1L, "Obi Wan", "NotMyKid", "EmpireSlayer23");
		when(repoMock.findByUsername(user.getUsername())).thenReturn(user);
		assertEquals(user, serviceImplMock.getUserByUsername(user.getUsername()));
	}

	@Test
	public void getMissingUserByUsernameThrows() {
		when(repoMock.findByUsername(anyString())).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> {
			serviceImplMock.getUserByUsername("R2D2");
		});
	}

	@Test
	public void getUsers() {
		List<User> newUsers = new ArrayList<User>(){
			{
				add(new User("Yoda","896 BBY","HighMaster"));
				add(new User("Boba Fett","KaminoHome","MissYoyDad"));
			}
		};
		when(repoMock.findAll()).thenReturn(newUsers);
		assertEquals(2, serviceImplMock.getUsers().size());
	}

}
