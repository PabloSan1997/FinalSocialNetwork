package com.example.demo.services.utils;

import com.example.demo.models.entities.Roles;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InitialValues {
    @Autowired
    private RoleRepository roleRepository;

    public void viewAndGeneratedRoles(){
        String[] rolesname = {"USER", "ADMIN"};
        List<Roles> roles = new ArrayList<>();
        for (String name:rolesname) {
            Optional<Roles> oRole = roleRepository.findByName(name);
            if(oRole.isEmpty())
                roles.add(Roles.builder().name(name).build());
        }
        if(roles.size()>0)
            roleRepository.saveAll(roles);
    }
}
