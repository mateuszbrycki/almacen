package com.almacen.module.storage.repository;

import com.almacen.module.file.UserFile;
import com.almacen.module.storage.FileFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileFolderRepository extends JpaRepository<FileFolder, Integer> {

    @Query("SELECT f FROM FileFolder f WHERE Folder.id = :folderId")
    List<FileFolder> findByFolderId(@Param("folderId") Integer folderId);
}
