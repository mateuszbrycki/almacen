package com.almacen.module.user.dao;


import com.almacen.module.user.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);

    Boolean checkIfUserWithMailExists(String mail);

    Boolean checkIfUserWithUsernameExists(String username);

    User findUserById(Integer userId);

    User findUserByUsername(String username);

    List<User> findUsersByUsername(String username);

    List<User> findUsersByUsername(String username, Integer userId);

    Integer getUserIdByUsername(String username);
}

