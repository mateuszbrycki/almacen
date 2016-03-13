package com.almacen.module.user.service;

import com.almacen.module.user.User;
import com.almacen.module.user.dao.UserDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
@Transactional(value = "transactionManagerMySQL")
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;


    @Override
    public void saveUser(User user) { userDao.saveUser(user);}

    @Override
    public void updateUser(User user) { userDao.updateUser(user);}

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public Boolean checkIfUserWithMailExists(String mail) {
        return userDao.checkIfUserWithMailExists(mail);
    }

    @Override
    public Boolean checkIfUserWithUsernameExists(String username) {
       return userDao.checkIfUserWithUsernameExists(username);
    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public Integer getUserIdByUsername(String username){ return userDao.getUserIdByUsername(username);}

    @Override
    public List<User> findUsersByUsername(String username) {
        return userDao.findUsersByUsername(username);
    }

    @Override
    public List<User> findUsersByUsername(String username, Integer userId) {
        return userDao.findUsersByUsername(username, userId);
    }

    @Override
    public Boolean registerUser(User user) {

        Boolean userMailExists = this.checkIfUserWithMailExists(user.getMail());
        Boolean usernameExist = this.checkIfUserWithUsernameExists(user.getUsername());

        if(userMailExists || usernameExist) {
            return false;
        }

        this.saveUser(user);

        return true;
    }
}

