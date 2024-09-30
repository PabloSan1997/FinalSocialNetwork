package com.example.demo.controller;

import com.example.demo.models.dtos.LoginDto;
import com.example.demo.models.dtos.RegisterDto;
import com.example.demo.services.FollowerService;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FollowerService followerService;

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
    @GetMapping("/follow/count/{id}")
    public ResponseEntity<?> followingCounts(@PathVariable Long id){
        return ResponseEntity.ok(followerService.countFollows(id));
    }
    @GetMapping("/follow/find/followers/{id}")
    public ResponseEntity<?> findFollower(@PathVariable Long id, Pageable pageable){
        return ResponseEntity.ok(followerService.followers(id, pageable));
    }
    @GetMapping("/follow/find/followings/{id}")
    public ResponseEntity<?> findFollowings(@PathVariable Long id, Pageable pageable){
        return ResponseEntity.ok(followerService.followings(id, pageable));
    }
    @GetMapping("/follow/followUser")
    public ResponseEntity<?> findIsFollowing(@RequestParam String username){
        return ResponseEntity.ok(followerService.followingThisUser(username));
    }
    @PostMapping("/follow/{id}")
    public ResponseEntity<?> saveFollow(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED).body(followerService.save(id));
    }

}
