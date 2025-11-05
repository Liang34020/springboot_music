package com.anthony.springboot_music.controller;

import com.anthony.springboot_music.dto.UserRegisterRequest;
import com.anthony.springboot_music.model.User;
import com.anthony.springboot_music.service.UserService;
import com.anthony.springboot_music.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
