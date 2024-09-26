package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadImplementationtException;
import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.exceptions.MyNotFoundException;
import com.example.demo.models.dtos.LoginDto;
import com.example.demo.models.dtos.LoginResponse;
import com.example.demo.models.dtos.RegisterDto;
import com.example.demo.models.dtos.UserSecurityDto;
import com.example.demo.models.entities.LoginRegister;
import com.example.demo.models.entities.Roles;
import com.example.demo.models.entities.UserInfo;
import com.example.demo.models.entities.Users;
import com.example.demo.repositories.LoginRegisterRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserInfoRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private LoginRegisterRepository loginRegisterRepository;

    @Override
    @Transactional
    public LoginResponse login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        Authentication authorizationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            Authentication authentication = authenticationManager.authenticate(authorizationToken);
            UserSecurityDto userSecurityDto = (UserSecurityDto) authentication.getPrincipal();

            return LoginResponse.builder()
                    .username(username).jwtoken(userSecurityDto.getToken()).build();
        } catch (Exception e) {
            throw new MyBadRequestException("Usuario o contraseña incorrectos");
        }
    }

    @Override
    @Transactional
    public LoginResponse register(RegisterDto registerDto) {
        String username = registerDto.getUsername();
        String password = passwordEncoder.encode(registerDto.getPassword());
        String nickname = registerDto.getNickname();
        Optional<Users> oUser = userRepository.findByUsername(username);
        Users user = null;
        if (oUser.isEmpty()){
            user = new Users();
            user.setUsername(username);
        }
        else {
            if (!oUser.get().getEnabled()) {
                user = oUser.get();
                user.setEnabled(true);
            }else{
                throw new MyBadRequestException("Ususario ocupado");
            }
        }
        Roles role = roleRepository.findByName("USER").orElseThrow();
        List<Roles> roles = new ArrayList<>();
        roles.add(role);
        UserInfo userInfo = UserInfo.builder()
                .urlPerfil(registerDto.getUrlPerfil())
                .description(registerDto.getDescription())
                .build();
        user.setNickname(nickname);
        user.setPassword(password);
        user.setRoles(roles);
        user.setUserInfo(userInfo);
        Users userSave = userRepository.save(user);
        LoginDto loginDto = new LoginDto();
        loginDto.setPassword(registerDto.getPassword());
        loginDto.setUsername(userSave.getUsername());
        return login(loginDto);
    }

    @Override
    @Transactional
    public UserInfo viewUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        return userRepository.findByUsername(username).orElseThrow().getUserInfo();
    }

    @Override
    @Transactional
    public void logout(String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        LoginRegister loginRegister = loginRegisterRepository.findByJwtoken(token).orElseThrow();
        loginRegister.setEnabled(false);
        loginRegisterRepository.save(loginRegister);
    }

    @Override
    @Transactional
    public UserInfo findPerfil(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->{
            throw new MyNotFoundException("No existe usuario");
        }).getUserInfo();
    }

}
