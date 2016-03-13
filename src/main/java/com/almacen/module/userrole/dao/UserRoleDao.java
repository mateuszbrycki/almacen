package com.almacen.module.userrole.dao;


import com.almacen.module.userrole.UserRole;

import java.util.Set;

public interface UserRoleDao {

    void saveUserRole(UserRole userRole);

    UserRole findById(Integer id);

    UserRole findByName(String role);

    Set<UserRole> findByUserId(Integer userId);
}

