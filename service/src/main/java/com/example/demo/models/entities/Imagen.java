package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "imagenes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "url_image", length = 2500)
    private String urlImage;
    @Column(length = 250)
    private String description;
    @Column(name = "create_at")
    private Date createAt;
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties({"images", "roles", "id", "createAt", "enabled", "updateAt"})
    private Users user;

    @OneToMany(mappedBy = "image", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comments> comments;

    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }

}
