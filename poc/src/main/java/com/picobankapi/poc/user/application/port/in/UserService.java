package com.picobankapi.poc.user.application.port.in;

import java.util.List;

import com.picobankapi.poc.user.domain.User;

public interface UserService {
    List<User> getUsers();

    User getUserByUsername(String username);

    User registerUser(User user);
}
