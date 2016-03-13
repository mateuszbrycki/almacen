package com.almacen.module.userrole.dao;

import com.almacen.AbstractDaoMySQL;
import com.almacen.module.userrole.UserRole;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends AbstractDaoMySQL implements UserRoleDao {

    public void saveUserRole(UserRole userRole) {
        persist(userRole);
    }

    @Override
    public UserRole findById(Integer id) {
        Query query = getSession().createSQLQuery("select * from user_role where role_id = :id");
        query.setInteger("id", id);

        return this.mapUserRoleObject(query.list());
    }

    @Override
    public UserRole findByName(String role) {
        Query query = getSession().createSQLQuery("select * from user_role where role = :role");
        query.setString("role", role);

        return this.mapUserRoleObject(query.list());
    }

    @Override
    public Set<UserRole> findByUserId(Integer id) {
        Query query = getSession().createSQLQuery("select * from user_role where role_id = :id");
        query.setInteger("id", id);

        return new HashSet<UserRole>();
    }

    private UserRole mapUserRoleObject(List<Object[]> userRoleObj) {

        UserRole userRole = new UserRole();
        userRole.setId((Integer)(userRoleObj.get(0))[0]);
        userRole.setRole((String)(userRoleObj.get(0))[1]);

        return userRole;
    }

}
