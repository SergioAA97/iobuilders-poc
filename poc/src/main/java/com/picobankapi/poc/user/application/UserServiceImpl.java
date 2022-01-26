package com.picobankapi.poc.user.application;

import java.util.List;

import com.picobankapi.poc.core.domain.InvalidArgumentsException;
import com.picobankapi.poc.user.application.events.UserRegisteredEventEmitter;
import com.picobankapi.poc.user.application.port.in.UserService;
import com.picobankapi.poc.user.application.port.out.UserRepository;
import com.picobankapi.poc.user.domain.User;
import com.picobankapi.poc.user.domain.UserAlreadyExistsException;
import com.picobankapi.poc.user.domain.UserNotFoundException;
import com.picobankapi.poc.user.domain.UserRegisteredEvent;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserRegisteredEventEmitter eventEmitter;

    @Override
    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        User result = repository.findByUsername(username);
        if (result == null)
            throw new UserNotFoundException(username);
        return result;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        if (user == null) {
            throw new InvalidArgumentsException("");
        }
        String username = user.getUsername();
        if (username != null) {
            if (repository.findByUsername(username) != null) {
                throw new UserAlreadyExistsException(user.getUsername());
            }
        }
        User newUser = repository.save(user);
        eventEmitter.publish(new UserRegisteredEvent(this, newUser));
        return newUser;
    }

}
