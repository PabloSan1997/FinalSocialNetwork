package com.example.demo.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserShowInfoDto {
    private Long id;
    private String username;
    private String nickname;
    private String description;
    private String urlPerfil;
    private Long following;
    private Long followers;
}
