package com.picobankapi.poc.user.adapter.in;

import java.util.List;

import javax.validation.Valid;

import com.picobankapi.poc.user.application.UserServiceImpl;
import com.picobankapi.poc.user.domain.User;
import com.picobankapi.poc.user.domain.UserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllerREST {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public void registerUser(@Valid @RequestBody User user) throws UserException {
        userService.registerUser(user);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) throws UserException {
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public List<User> listUsers() {
        return userService.getUsers();
    }

}
