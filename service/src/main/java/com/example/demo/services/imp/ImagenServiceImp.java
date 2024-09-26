package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.exceptions.MyNotFoundException;
import com.example.demo.models.dtos.SaveImageDto;
import com.example.demo.models.dtos.ShowImageDto;
import com.example.demo.models.entities.Comments;
import com.example.demo.models.entities.Imagen;
import com.example.demo.models.entities.Users;
import com.example.demo.repositories.CommentsRepository;
import com.example.demo.repositories.ImagesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ImagenServiceImp implements ImagenService {
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    @Transactional
    public List<ShowImageDto> findAll(Pageable pageable) {
        List<ShowImageDto> images = imagesRepository.findAllByCreate(pageable);
        return images.stream().peek(p -> {
            Long count = commentsRepository.countComentsByImageId(p.getId());
            p.setComments(count);
        }).toList();
    }

    @Override
    @Transactional
    public List<ShowImageDto> findByUsername(Pageable pageable, String username) {
        List<ShowImageDto> images = imagesRepository.findAllByUsername(username, pageable);
        return images.stream().peek(p -> {
            Long count = commentsRepository.countComentsByImageId(p.getId());
            p.setComments(count);
        }).toList();

    }

    @Override
    @Transactional
    public Imagen findById(UUID id, Pageable pageable) {
        Imagen imagen = imagesRepository.findById(id).orElseThrow(() -> {
            throw new MyNotFoundException("No se encontró imagen");
        });
        List<Comments> comments = commentsRepository.findCommentsByImageId(id, pageable);
        imagen.setComments(comments);
        return imagen;
    }

    @Override
    @Transactional
    public Imagen save(SaveImageDto saveImageDto) {
        Users user = getUserAuthentication();
        Imagen imagen = Imagen.builder()
                .description(saveImageDto.getDescription())
                .urlImage(saveImageDto.getUrlImage())
                .user(user).build();
        return imagesRepository.save(imagen);
    }

    @Override
    @Transactional
    public void deleteImage(UUID id) {
        String username = getUserAuthentication().getUsername();
        Imagen imagen = imagesRepository.findByIdAndUsername(id, username).orElseThrow(() -> {
            throw new MyBadRequestException("No tienes permiso para borrar esta imagen");
        });
        imagesRepository.deleteById(imagen.getId());
    }

    private Users getUserAuthentication() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new MyBadRequestException("Error con el usuario");
        });
    }
}
