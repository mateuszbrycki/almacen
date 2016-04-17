package com.almacen.module.user.service;


import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);

    Boolean checkIfUserWithMailExists(String mail);

    Boolean checkIfUserWithUsernameExists(String username);

    User findUserById(Integer userId) throws UserNotFoundException;

    User findUserByUsername(String username) throws UserNotFoundException;

    List<User> findUsersByUsername(String username) throws UserNotFoundException;

    List<User> findUsersByUsername(String username, Integer userId) throws UserNotFoundException;

    List<User> findAllUser() throws UserNotFoundException;

    Integer getUserIdByUsername(String username) throws UserNotFoundException;

    Boolean registerUser(User user);

}
