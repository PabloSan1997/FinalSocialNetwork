package com.example.demo.repositories;


import com.example.demo.models.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {

}
