package com.example.demo.repositories;

import com.example.demo.models.entities.Imagen;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImagesRepository extends CrudRepository<Imagen, UUID> {
    @Query("select i from Imagen i order by i.createAt desc")
    List<Imagen> findAllByCreate(Pageable pageable);
    @Query("select i from Imagen i where i.user.username=?1 order by i.createAt desc")
    List<Imagen> findAllByUsername(String username, Pageable pageable);
    @Query("select i from Imagen i where i.id = ?1 and i.user.username=?2")
    Optional<Imagen> findByIdAndUsername(UUID id, String username);
}
