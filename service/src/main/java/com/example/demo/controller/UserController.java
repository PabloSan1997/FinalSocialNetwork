package com.example.demo.controller;

import com.example.demo.models.dtos.LoginDto;
import com.example.demo.models.dtos.RegisterDto;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto){
        return ResponseEntity.ok(userService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.login(loginDto));
    }
    @GetMapping("/userInfo")
    public ResponseEntity<?> findUserInfo(){
        return ResponseEntity.ok(userService.viewUserInfo());
    }
    @GetMapping("/otherUser")
    public ResponseEntity<?> findByUsername(@RequestParam(name = "username") String username){
        return ResponseEntity.ok(userService.findPerfil(username));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String bearerToken){
        userService.logout(bearerToken);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/disable")
    public ResponseEntity<?> disableCount(){
        userService.disableCount();
        return ResponseEntity.noContent().build();
    }
}
