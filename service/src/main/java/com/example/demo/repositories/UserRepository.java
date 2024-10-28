package com.example.demo.repositories;

import com.example.demo.models.entities.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    @Query("select u from Users u where u.username like %?1%")
    List<Users> findLikeUsernames(String username, Pageable pageable);
    @Query("select u from Users u where u.nickname like %?1%")
    List<Users> findLikeNicknames(String nickname, Pageable pageable);
}
