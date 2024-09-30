package com.example.demo.repositories;

import com.example.demo.models.entities.Following;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FollowingRepository extends CrudRepository<Following, Long> {
    @Query("select f From Following f where f.user1.id=?1 and f.user2.id=?2")
    Optional<Following> findFollowingByUsers(Long idUser1, Long idUSer2);

    @Query("select f From Following f where f.user1.id=?1")
    List<Following> findAllUsersFollowings(Long id, Pageable pageable);

    @Query("select f From Following f where f.user2.id=?2")
    List<Following> findAllUserFollowers(Long id, Pageable pageable);

    @Query("select count(f) From Following f where f.user2.id=?1")
    Long countFollowings(Long id);
    @Query("select count(f) From Following f where f.user2.id=?2")
    Long countFollowers(Long id);


}
