package com.example.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaveImageDto {
    @Size(max = 2500)
    @NotBlank
    private String urlImage;
    @NotBlank
    @Size(max = 250)
    private String description;
}
