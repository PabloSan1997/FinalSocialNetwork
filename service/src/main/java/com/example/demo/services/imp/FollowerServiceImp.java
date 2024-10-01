package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.models.dtos.FollowingDto;
import com.example.demo.models.dtos.ShowCountFollow;
import com.example.demo.models.dtos.ShowUsersFollow;
import com.example.demo.models.entities.Following;
import com.example.demo.models.entities.Users;
import com.example.demo.repositories.FollowingRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class FollowerServiceImp implements FollowerService {
    @Autowired
    private FollowingRepository followingRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public FollowingDto save(Long idUser2) {
        Users user1 = getAuthentication();
        Long idUser1 = user1.getId();
        if(Objects.equals(idUser1, idUser2))
            throw new MyBadRequestException("No te puedes seguir a ti mismo");
        Users user2 = userRepository.findById(idUser2).orElseThrow(() -> {
            throw new MyBadRequestException("User invalid");
        });
        FollowingDto followingDto = new FollowingDto();
        followingRepository.findFollowingByUsers(idUser1, idUser2).ifPresentOrElse(f -> {
            followingRepository.deleteById(f.getId());
            followingDto.setFollowingThisUser(false);
        }, () -> {
            Following following = Following.builder()
                    .user1(user1).user2(user2).build();
            followingRepository.save(following);
            followingDto.setFollowingThisUser(true);

        });
        return followingDto;
    }

    @Override
    @Transactional
    public ShowUsersFollow followings(Long id, Pageable pageable) {
        List<Users> users = followingRepository.findAllUsersFollowings(id, pageable).stream()
                .map(Following::getUser2).toList();
        return ShowUsersFollow.builder().users(users).build();
    }

    @Override
    @Transactional
    public ShowUsersFollow followers(Long id, Pageable pageable) {
        List<Users> users = followingRepository.findAllUserFollowers(id, pageable).stream()
                .map(Following::getUser1).toList();
        return ShowUsersFollow.builder().users(users).build();
    }

    @Override
    @Transactional
    public ShowCountFollow countFollows(Long id) {
        Long countFollowings = followingRepository.countFollowings(id);
        Long countFollowers = followingRepository.countFollowers(id);

        return ShowCountFollow.builder().followers(countFollowers).following(countFollowings).build();
    }

    @Override
    @Transactional
    public FollowingDto followingThisUser(String username) {
        Long idUser1 = getAuthentication().getId();
        Long idUser2 = userRepository.findByUsername(username).orElseThrow(()->{
            throw new MyBadRequestException("Invalid Username");
        }).getId();
        boolean isfollowing = followingRepository.findFollowingByUsers(idUser1, idUser2).isPresent();
        return FollowingDto.builder().followingThisUser(isfollowing).build();
    }

    private Users getAuthentication() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username).orElseThrow();
    }
}
