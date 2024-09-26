package com.example.demo.repositories;

import com.example.demo.models.entities.Comments;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentsRepository extends CrudRepository<Comments, Long> {
    List<Comments> findAll(Pageable pageable);
    @Query("select count(c) from Comments c where c.image.id=?1")
    Long countComentsByImageId(UUID id);
    @Query("select c from Comments c where c.image.id=?1")
    List<Comments> findCommentsByImageId(UUID id, Pageable pageable);
    @Query("select i from Comments i where i.id = ?1 and i.user.username=?2")
    Optional<Comments> findByIdAndUsername(Long id, String username);
}
