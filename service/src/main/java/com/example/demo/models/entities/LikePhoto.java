package com.example.demo.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "like_photo")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "like_date")
    private Date likeDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "id_image")
    private Imagen image;

    @PrePersist
    public void prePersist(){
        likeDate = new Date();
    }
}
