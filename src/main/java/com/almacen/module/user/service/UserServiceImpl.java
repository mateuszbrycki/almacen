package com.almacen.module.user.service;

import com.almacen.module.user.User;
import com.almacen.module.user.exception.UserNotFoundException;
import com.almacen.module.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(Integer id) {
        this.userRepository.delete(id);
    }

    @Override
    public Boolean checkIfUserWithMailExists(String mail) {
        User user = this.userRepository.findOneByMail(mail);

        if(user != null) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean checkIfUserWithUsernameExists(String username) {
        User user = this.userRepository.findOneByUsername(username);

        if(user != null) {
            return true;
        }

        return false;
    }

    @Override
    public User findUserById(Integer userId) throws UserNotFoundException {
        User user = this.userRepository.findOne(userId);

        if(user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        User user = this.userRepository.findOneByUsername(username);

        if(user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public List<User> findUsersByUsername(String username) throws UserNotFoundException {
        List<User> users = this.userRepository.findAllByUsernameContaining(username);

        if(users == null) {
            throw new UserNotFoundException();
        }

        return users;
    }

    @Override
    public List<User> findUsersByUsername(String username, Integer userId) throws UserNotFoundException  {
        List<User> users = this.userRepository.findAllByIdAndUsernameContaining(username, userId);

        if(users == null) {
            throw new UserNotFoundException();
        }

        return users;
    }

    @Override
    public List<User> findAllUser() throws UserNotFoundException {
        List<User> users = this.userRepository.findAll();

        return users;
    }

    @Override
    public Integer getUserIdByUsername(String username) throws UserNotFoundException {
        User user = this.userRepository.findOneByUsername(username);

        if(user == null) {
            throw new UserNotFoundException();
        }

        return user.getId();
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
