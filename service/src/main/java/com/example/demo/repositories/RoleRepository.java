package com.example.demo.repositories;

import com.example.demo.models.entities.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}