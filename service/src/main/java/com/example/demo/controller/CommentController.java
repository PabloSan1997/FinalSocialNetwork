package com.example.demo.controller;

import com.example.demo.models.dtos.CommentDto;
import com.example.demo.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> newComment(@Valid @RequestBody CommentDto commentDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(commentDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
