package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    @Size(max = 50, min = 4)
    @NotBlank
    private String username;
    @Size(max = 50, min = 4)
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
    @NotBlank
    @Size(min = 5)
    private String urlPerfil;
    @NotBlank
    @Size(max = 250)
    private String description;

}
