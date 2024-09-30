package com.example.demo.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "following")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "follower_date")
    private Date followerDate;
    @ManyToOne
    @JoinColumn(name = "id_user1")
    @JsonIgnore
    private Users user1;
    @ManyToOne
    @JoinColumn(name = "id_user2")
    @JsonIgnore
    private Users user2;

    @PrePersist
    public void prePersist(){
        followerDate = new Date();
    }
}
