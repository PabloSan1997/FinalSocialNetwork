package com.example.demo.models.dtos;

import com.example.demo.models.entities.Comments;
import com.example.demo.models.entities.Imagen;
import com.example.demo.models.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowOneImage {
    private UUID id;
    private String urlImage;
    private String description;
    private Date createAt;
    @JsonIgnoreProperties({"images", "roles", "id", "createAt", "enabled", "updateAt"})
    private Users user;
    private List<Comments> comments;
    private Long likes;
    public ShowOneImage(Imagen imagen){
        this.id = imagen.getId();
        this.urlImage = imagen.getUrlImage();
        this.description = imagen.getDescription();
        this.createAt = imagen.getCreateAt();
        this.user = imagen.getUser();
    }
}
