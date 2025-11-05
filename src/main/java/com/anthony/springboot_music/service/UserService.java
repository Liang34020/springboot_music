package com.anthony.springboot_music.service;


import com.anthony.springboot_music.dto.UserLoginRequest;
import com.anthony.springboot_music.dto.UserRegisterRequest;
import com.anthony.springboot_music.model.User;

public interface UserService {

    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User login(UserLoginRequest userLoginRequest);

}

