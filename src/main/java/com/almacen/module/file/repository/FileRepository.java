package com.almacen.module.file.repository;

import com.almacen.module.file.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<UserFile, Integer> {

    /*@Query("SELECT uf FROM UserFile uf WHERE uf.user.id = :userId")
    List<UserFile> findUserFilesByUserId(@Param("usedId") Integer userId);*/


}
