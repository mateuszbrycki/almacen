package com.almacen.module.folder.repository;


import com.almacen.module.folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder,Integer> {


    @Query("SELECT f FROM Folder f WHERE f.id = :folderId")
    Folder findOneByFolderId(@Param("folderId") Integer id);

    @Query("SELECT f FROM Folder f WHERE f.physical_path = :physicalPath")
    Folder findOneByPhysicalPath(@Param("physicalPath") String physical_path);

    @Query("SELECT f FROM Folder f WHERE f.user.id = :userId AND f.id =:folderId")
    Folder findOneByUserIdAndFolderId(@Param("userId") Integer id,@Param("folderId") Integer folderId);

    @Query("SELECT f FROM Folder f WHERE f.physical_path = :physicalPath ORDER BY f.folder_name DESC")
    List<Folder> findAllByPhysicalPath(@Param("physicalPath") String physical_path);

    @Query("SELECT f FROM Folder f WHERE f.physical_path = :physicalPath AND f.folder_name = :folderName")
    Folder findOneByFolderNameAndPhysicalPath(@Param("physicalPath") String physical_path,@Param("folderName") String folder_name);



}
