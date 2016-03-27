package com.almacen.module.userrole.repository;

import com.almacen.module.userrole.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findOneByRole(String role);
}
