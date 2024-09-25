package com.example.demo.repositories;

import com.example.demo.models.entities.LoginRegister;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginRegisterRepository extends CrudRepository<LoginRegister, Long> {

    Optional<LoginRegister> findByJwtoken(String token);

}
