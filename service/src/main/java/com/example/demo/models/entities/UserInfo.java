package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 2500, name = "url_perfil")
    private String urlPerfil;
    @Column(length = 250)
    private String description;
    @OneToOne(mappedBy = "userInfo")
    @JsonIgnoreProperties({"roles", "userInfo"})
    private Users user;
}
