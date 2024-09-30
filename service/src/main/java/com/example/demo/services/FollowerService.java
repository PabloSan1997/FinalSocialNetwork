package com.example.demo.services;

import com.example.demo.models.dtos.FollowingDto;
import com.example.demo.models.dtos.ShowCountFollow;
import com.example.demo.models.dtos.ShowUsersFollow;
import com.example.demo.models.entities.Following;
import com.example.demo.models.entities.Users;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowerService {
    FollowingDto save(Long idUser2);
    ShowUsersFollow followings(Long id, Pageable pageable);
    ShowUsersFollow followers(Long id, Pageable pageable);
    ShowCountFollow countFollows(Long id);
    FollowingDto followingThisUser(String username);
}
