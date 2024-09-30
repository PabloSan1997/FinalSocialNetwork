package com.example.demo.models.dtos;

import com.example.demo.models.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowUsersFollow {
    @JsonIgnoreProperties({"images", "roles", "id", "createAt", "enabled", "updateAt", "comments"})
    private List<Users> users;
}
