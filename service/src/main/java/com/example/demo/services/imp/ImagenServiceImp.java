package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.exceptions.MyNotFoundException;
import com.example.demo.models.dtos.SaveImageDto;
import com.example.demo.models.dtos.ShowImageDto;
import com.example.demo.models.dtos.ShowLikeDto;
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
            p.setComments(count);
            ShowLikeDto showLikeDto = viewImageLikes(p.getId());
            p.setUserLike(showLikeDto.getUserLike());
            p.setLikes(showLikeDto.getCountLikes());
        }).toList();
    }

    @Override
    @Transactional
    public List<ShowImageDto> findByUsername(Pageable pageable, String username) {
        List<ShowImageDto> images = imagesRepository.findAllByUsername(username, pageable);
        return images.stream().peek(p -> {
            Long count = commentsRepository.countComentsByImageId(p.getId());
            p.setComments(count);
            ShowLikeDto showLikeDto = viewImageLikes(p.getId());
            p.setUserLike(showLikeDto.getUserLike());
            p.setLikes(showLikeDto.getCountLikes());
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
        showOneImage.setComments(comments);
        ShowLikeDto showLikeDto = viewImageLikes(id);
        showOneImage.setUserLike(showLikeDto.getUserLike());
        showOneImage.setLikes(showLikeDto.getCountLikes());
        return showOneImage;
    }

    @Override
    @Transactional
    public ShowImageDto save(SaveImageDto saveImageDto) {
        Users user = getUserAuthentication();
        Imagen imagen = Imagen.builder()
                .description(saveImageDto.getDescription())
                .urlImage(saveImageDto.getUrlImage())
                .user(user)
                .likes(new ArrayList<>())
                .comments(new ArrayList<>()).build();

        Imagen imagenRes = imagesRepository.save(imagen);
        ShowImageDto showImageDto = imagesRepository.findShowImageId(imagenRes.getId()).orElseThrow();
        showImageDto.setLikes(0L);
        showImageDto.setUserLike(false);
        showImageDto.setComments(0L);
        return showImageDto;
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
    public ShowLikeDto checkLike(UUID idImage) {
        Users users = getUserAuthentication();
        Imagen imagen = imagesRepository.findById(idImage).orElseThrow(()->{
            throw new MyBadRequestException("id invalid");
        });
        ShowLikeDto showLikeDto = ShowLikeDto.builder().idImage(idImage).build();
        likePhotoRepository.findByUserAndImage(users.getId(), imagen.getId()).ifPresentOrElse(
                l ->{
                    likePhotoRepository.deleteById(l.getId());
                    Long likesImage = likePhotoRepository.countLikeByImageId(idImage);
                    showLikeDto.setUserLike(false);
                    showLikeDto.setCountLikes(likesImage);
                },()->{
                    LikePhoto likePhoto = LikePhoto.builder()
                            .image(imagen).user(users).build();
                    likePhotoRepository.save(likePhoto);
                    Long likesImage = likePhotoRepository.countLikeByImageId(idImage);
                    showLikeDto.setUserLike(true);
                    showLikeDto.setCountLikes(likesImage);
                }
        );
        return showLikeDto;
    }

    private Users getUserAuthentication() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new MyBadRequestException("Error con el usuario");
        });
    }

    private ShowLikeDto viewImageLikes(UUID id){
        Users user = getUserAuthentication();
        Long likesCount = likePhotoRepository.countLikeByImageId(id);
        boolean isUserLike = likePhotoRepository.findByUserAndImage(user.getId(), id).isPresent();
        return ShowLikeDto.builder().countLikes(likesCount).userLike(isUserLike).idImage(id).build();
    }
}
