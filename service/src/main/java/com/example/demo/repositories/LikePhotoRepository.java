package com.example.demo.repositories;

import com.example.demo.models.entities.LikePhoto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LikePhotoRepository extends CrudRepository<LikePhoto, Long> {
    @Query("select count(l) from LikePhoto l where l.image.id=?1")
    Long countLikeByImageId(UUID id);
}
