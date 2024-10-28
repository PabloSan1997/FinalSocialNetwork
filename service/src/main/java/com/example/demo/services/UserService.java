package com.example.demo.services;

import com.example.demo.models.dtos.*;
import com.example.demo.models.entities.UserInfo;
import org.springframework.data.domain.Pageable;

public interface UserService {
    LoginResponse login(LoginDto loginDto);
    LoginResponse register(RegisterDto registerDto);
    UserShowInfoDto viewUserInfo();
    void logout(String token);
    UserShowInfoDto findPerfil(String username);
    void disableCount();
    SearchUserDto searchUser(String name, Pageable pageable);
}
