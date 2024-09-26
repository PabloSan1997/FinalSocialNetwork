package com.example.demo.services;

import com.example.demo.models.dtos.LoginDto;
import com.example.demo.models.dtos.LoginResponse;
import com.example.demo.models.dtos.RegisterDto;
import com.example.demo.models.entities.UserInfo;

public interface UserService {
    LoginResponse login(LoginDto loginDto);
    LoginResponse register(RegisterDto registerDto);
    UserInfo viewUserInfo();
    void logout(String token);
    UserInfo findPerfil(String username);
}
