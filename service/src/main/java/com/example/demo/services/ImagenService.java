package com.example.demo.services;

import com.example.demo.models.dtos.SaveImageDto;
import com.example.demo.models.dtos.ShowImageDto;
import com.example.demo.models.entities.Imagen;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

public interface ImagenService {
    List<ShowImageDto> findAll(Pageable pageable);
    List<ShowImageDto> findByUsername(Pageable pageable, String username);
    Imagen findById(UUID id, Pageable pageable);
    Imagen save(SaveImageDto saveImageDto);
    void deleteImage(UUID id);

}
