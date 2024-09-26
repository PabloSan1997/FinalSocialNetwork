package com.example.demo.services.imp;

import com.example.demo.exceptions.MyBadRequestException;
import com.example.demo.models.dtos.CommentDto;
import com.example.demo.models.entities.Comments;
import com.example.demo.models.entities.Imagen;
import com.example.demo.models.entities.Users;
import com.example.demo.repositories.CommentsRepository;
import com.example.demo.repositories.ImagesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    @Transactional
    public Comments saveComment(CommentDto commentDto) {
        Users user = getUserAuthentication();
        Imagen imagen = imagesRepository.findById(UUID.fromString(commentDto.getIdImage())).orElseThrow(()->{
            throw new MyBadRequestException("Id image invalid");
        });
        Comments comments = Comments.builder()
                .comment(commentDto.getComment()).user(user).image(imagen).build();
        return commentsRepository.save(comments);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Users user = getUserAuthentication();
        Comments comment = commentsRepository.findByIdAndUsername(id, user.getUsername()).orElseThrow(() -> {
            throw new MyBadRequestException("Id invalido");
        });
        commentsRepository.deleteById(comment.getId());
    }

    private Users getUserAuthentication() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(username).orElseThrow(() -> {
            throw new MyBadRequestException("Error con el usuario");
        });
    }
}
