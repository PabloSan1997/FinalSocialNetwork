package com.example.demo.services;

import com.example.demo.models.dtos.CommentDto;
import com.example.demo.models.entities.Comments;

public interface CommentService {
    Comments saveComment(CommentDto commentDto);
    void deleteById(Long id);
}
