package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "login_register")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String jwtoken;
    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "update_at")
    private Date updateAt;
    private Boolean enabled;
    @PrePersist
    public void prePersist(){
        enabled = true;
        createAt = new Date();
        updateAt = new Date();
    }
    @PreUpdate
    public void preUpdate(){
        updateAt = new Date();
    }

    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;
}
