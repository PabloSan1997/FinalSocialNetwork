package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_at")
    private Date createAt;
    @Column(length = 250, nullable = false)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnoreProperties({"images", "roles", "id", "createAt", "enabled", "updateAt", "comments"})
    private Users user;

    @ManyToOne
    @JoinColumn(name = "id_image")
    @JsonIgnore
    private Imagen image;
}
