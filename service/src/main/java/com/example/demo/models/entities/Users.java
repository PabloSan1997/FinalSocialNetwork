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

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 60)
    private String username;
    @Column(length = 60)
    private String nickname;
    @Column(length = 500)
    @JsonIgnore
    private String password;
    private Boolean enabled;

    @Column(name = "create_at")
    private Date createAt;
    @Column(name = "update_at")
    private Date updateAt;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_infor")
    @JsonIgnoreProperties({"user", "description"})
    private UserInfo userInfo;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LoginRegister> logins;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_user", "id_role"})}
    )
    private List<Roles> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Imagen> images;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comments> comments;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LikePhoto> likes;

    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<Following> following;
    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<Following> followers;
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

}
