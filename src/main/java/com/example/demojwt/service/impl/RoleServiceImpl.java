package com.example.demojwt.service.impl;

import com.example.demojwt.model.Role;
import com.example.demojwt.model.RoleName;
import com.example.demojwt.repository.RoleRepository;
import com.example.demojwt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
