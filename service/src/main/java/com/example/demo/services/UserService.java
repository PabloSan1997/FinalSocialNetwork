package com.example.demo.services;

import com.example.demo.models.dtos.LoginDto;
import com.example.demo.models.dtos.LoginResponse;
import com.example.demo.models.dtos.RegisterDto;
import com.example.demo.models.dtos.UserShowInfoDto;
import com.example.demo.models.entities.UserInfo;

public interface UserService {
    LoginResponse login(LoginDto loginDto);
    LoginResponse register(RegisterDto registerDto);
    UserShowInfoDto viewUserInfo();
    void logout(String token);
    UserShowInfoDto findPerfil(String username);
    void disableCount();
}
