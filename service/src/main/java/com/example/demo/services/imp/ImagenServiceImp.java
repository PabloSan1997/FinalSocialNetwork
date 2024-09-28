package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.exceptions.MyNotFoundException;
import com.example.demo.models.dtos.SaveImageDto;
import com.example.demo.models.dtos.ShowImageDto;
import com.example.demo.models.dtos.ShowOneImage;
import com.example.demo.models.entities.Comments;
import com.example.demo.models.entities.Imagen;
import com.example.demo.models.entities.LikePhoto;
import com.example.demo.models.entities.Users;
import com.example.demo.repositories.CommentsRepository;
import com.example.demo.repositories.ImagesRepository;
import com.example.demo.repositories.LikePhotoRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private LikePhotoRepository likePhotoRepository;

    @Override
    @Transactional
    public List<ShowImageDto> findAll(Pageable pageable) {
        Users users = getUserAuthentication();
        List<ShowImageDto> images = imagesRepository.findAllByCreate(pageable);
        return images.stream().peek(p -> {
            Long count = commentsRepository.countComentsByImageId(p.getId());
            Long countLiles = likePhotoRepository.countLikeByImageId(p.getId());
            boolean isAdminUSer = likePhotoRepository.findByUserAndImage(users.getId(), p.getId()).isPresent();
            p.setComments(count);
            p.setLikes(countLiles);
            p.setIsUserLike(isAdminUSer);
        }).toList();
    }

    @Override
    @Transactional
    public List<ShowImageDto> findByUsername(Pageable pageable, String username) {
        List<ShowImageDto> images = imagesRepository.findAllByUsername(username, pageable);
        return images.stream().peek(p -> {
            Long count = commentsRepository.countComentsByImageId(p.getId());
            Long counLikes = likePhotoRepository.countLikeByImageId(p.getId());
            p.setComments(count);
            p.setLikes(counLikes);
        }).toList();

    }

    @Override
    @Transactional
    public ShowOneImage findById(UUID id, Pageable pageable) {
        Imagen imagen = imagesRepository.findById(id).orElseThrow(() -> {
            throw new MyNotFoundException("No se encontró imagen");
        });
        ShowOneImage showOneImage = new ShowOneImage(imagen);
        List<Comments> comments = commentsRepository.findCommentsByImageId(id, pageable);
        Long likes = likePhotoRepository.countLikeByImageId(id);
        showOneImage.setComments(comments);
        showOneImage.setLikes(likes);
        return showOneImage;
    }

    @Override
    @Transactional
    public Imagen save(SaveImageDto saveImageDto) {
        Users user = getUserAuthentication();
        Imagen imagen = Imagen.builder()
                .description(saveImageDto.getDescription())
                .urlImage(saveImageDto.getUrlImage())
                .user(user)
                .likes(new ArrayList<>())
                .comments(new ArrayList<>()).build();
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

    @Override
    @Transactional
    public void checkLike(UUID idImage) {
        Users users = getUserAuthentication();
        Imagen imagen = imagesRepository.findById(idImage).orElseThrow(()->{
            throw new MyBadRequestException("id invalid");
        });
        likePhotoRepository.findByUserAndImage(users.getId(), imagen.getId()).ifPresentOrElse(
                l ->{
                    likePhotoRepository.deleteById(l.getId());
                },()->{
                    LikePhoto likePhoto = LikePhoto.builder()
                            .image(imagen).user(users).build();
                    likePhotoRepository.save(likePhoto);
                }
        );
    }

    private Users getUserAuthentication() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new MyBadRequestException("Error con el usuario");
        });
    }
}
