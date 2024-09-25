package com.example.demo.controller;

import com.example.demo.models.dtos.SaveImageDto;
import com.example.demo.services.ImagenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImagenController {
    @Autowired
    private ImagenService imagenService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.ok(imagenService.findAll(pageable));
    }
    @GetMapping("/user")
    public ResponseEntity<?> findByUsername(Pageable pageable, @RequestParam(name = "username") String username){
        return ResponseEntity.ok(imagenService.findByUsername(pageable, username));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(imagenService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        imagenService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SaveImageDto saveImageDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenService.save(saveImageDto));
    }
}
