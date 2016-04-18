package com.almacen.module.file.repository;

import com.almacen.module.file.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<UserFile, Integer> {

   List<UserFile> findByUserId(Integer userId);

   UserFile findOneByNameAndUserId(String name, Integer userId);
}
