package com.almacen.module.file.repository;

import com.almacen.module.file.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<UserFile, Integer> {

   List<UserFile> findByUserId(Integer userId);

}
