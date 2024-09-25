package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.models.dtos.UserSecurityDto;
import com.example.demo.models.entities.LoginRegister;
import com.example.demo.models.entities.Roles;
import com.example.demo.models.entities.Users;
import com.example.demo.repositories.LoginRegisterRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.utils.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private LoginRegisterRepository loginRegisterRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).orElseThrow(()->{
            throw new MyBadRequestException();
        });
        UserSecurityDto userSecurity = UserSecurityDto.builder()
                .username(username).password(user.getPassword()).enabled(user.getEnabled()).build();
        List<Roles> roles = user.getRoles();
        userSecurity.setRoles(roles);
        try {
            String token = jwtService.generateToken(userSecurity);
            userSecurity.setToken(token);
            LoginRegister loginRegister = LoginRegister.builder()
                    .jwtoken(token).user(user).build();
            loginRegisterRepository.save(loginRegister);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userSecurity;
    }
}
