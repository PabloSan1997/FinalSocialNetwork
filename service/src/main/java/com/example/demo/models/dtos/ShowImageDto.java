package com.example.demo.models.dtos;

import com.example.demo.models.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ShowImageDto {
    private UUID id;
    private String urlImage;
    private String description;
    private Date createAt;
    private String username;
    private String nickname;
    private String urlPerfil;
    private Long comments;
    private Long likes;
    private Boolean userLike;

    public ShowImageDto(UUID id, String urlImage, String description, Date createAt, Users users) {
        this.id = id;
        this.urlImage = urlImage;
        this.description = description;
        this.createAt = createAt;
        this.username = users.getUsername();
        this.nickname = users.getNickname();
        this.urlPerfil = users.getUserInfo().getUrlPerfil();
    }
}
