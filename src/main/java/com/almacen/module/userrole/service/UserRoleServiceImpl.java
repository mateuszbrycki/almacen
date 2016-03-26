package com.almacen.module.userrole.service;

import com.almacen.module.userrole.UserRole;
import com.almacen.module.userrole.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("userRoleService")
@Transactional
public class UserRoleServiceImpl implements UserRoleService  {

    @Resource
    private UserRoleRepository userRoleRepository;

    @Override
    public void saveUserRole(UserRole userRole) {
        userRoleRepository.save(userRole);
    }

    @Override
    public UserRole findByRole(String role) { return userRoleRepository.findOneByRole(role); }

    @Override
    public UserRole findById(Integer id) { return userRoleRepository.findOne(id); }


}

