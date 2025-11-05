package com.anthony.springboot_music.dao;

import com.anthony.springboot_music.dto.UserRegisterRequest;
import com.anthony.springboot_music.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
