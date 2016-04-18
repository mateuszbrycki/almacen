package com.almacen.module.user.repository;

import com.almacen.module.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findOneByMail(String mail);
    User findOneByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:username% AND u.id != :id")
    List<User> findAllByIdAndUsernameContaining(@Param("username") String username,
                                                @Param("id") Integer id);

    List<User> findAllByUsernameContaining(String username);

    @Query("SELECT u FROM User u")
    List<User> findAllUser();


}
