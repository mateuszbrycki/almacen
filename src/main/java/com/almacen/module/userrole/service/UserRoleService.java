package com.almacen.module.userrole.service;


import com.almacen.module.userrole.UserRole;

public interface UserRoleService {

    void saveUserRole(UserRole userRole);

    UserRole findByName(String role);

    UserRole findById(Integer id);
}
