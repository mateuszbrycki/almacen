package com.almacen.module.folder.repository;


import com.almacen.module.folder.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

    List<Folder> findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE Folder f SET f.folder_name = :folder_name WHERE f.id = :folderId")
    void updateFolderById(@Param("folderId") Integer folderId, @Param("folder_name") String folder_name);

    @Modifying
    @Query("UPDATE Folder f SET f.physical_path = :physical_path WHERE f.id = :folderId")
    void updateFolderPathById(@Param("physical_path") String physical_path, @Param("folderId") Integer folderId);

    @Query("SELECT f FROM Folder f WHERE f.id = :folderId")
    Folder findOneByFolderId(@Param("folderId") Integer folderId);

    @Query("SELECT f FROM Folder f WHERE f.physical_path = :physicalPath")
    Folder findOneByPhysicalPath(@Param("physicalPath") String physical_path);

    @Query("SELECT f FROM Folder f WHERE f.user.id = :userId AND f.id =:folderId")
    Folder findOneByUserIdAndFolderId(@Param("userId") Integer id, @Param("folderId") Integer folderId);

    @Query("SELECT f FROM Folder f WHERE f.physical_path = :physicalPath ORDER BY f.folder_name DESC")
    List<Folder> findAllByPhysicalPath(@Param("physicalPath") String physical_path);

    @Query("SELECT f FROM Folder f WHERE f.physical_path = :physicalPath AND f.folder_name = :folderName")
    Folder findOneByFolderNameAndPhysicalPath(@Param("physicalPath") String physical_path, @Param("folderName") String folder_name);

}
