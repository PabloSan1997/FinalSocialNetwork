package com.example.demo.services;

import com.example.demo.models.dtos.SaveImageDto;
import com.example.demo.models.entities.Imagen;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface ImagenService {
    List<Imagen> findAll(Pageable pageable);
    List<Imagen> findByUsername(Pageable pageable, String username);
    Imagen findById(UUID id);
    Imagen save(SaveImageDto saveImageDto);
    void deleteImage(UUID id);

}
