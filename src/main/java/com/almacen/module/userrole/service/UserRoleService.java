package com.almacen.module.userrole.service;


import com.almacen.module.userrole.UserRole;

public interface UserRoleService {

    void saveUserRole(UserRole userRole);

    UserRole findByRole(String role);

    UserRole findById(Integer id);
}
