package com.almacen.module.file.repository;

import com.almacen.module.file.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<UserFile, Integer> {

    List<UserFile> findByUserId(Integer userId);

    @Query(value = "SELECT * FROM file where file_id IN " +
            "(SELECT fk_file_id FROM file_folder WHERE fk_folder_id = :folderId)", nativeQuery = true)
    List<UserFile> findByFolderId(@Param("folderId") Integer folderId);

    @Modifying
    @Query(value = "DELETE FROM UserFile f WHERE f.id IN " +
            "(SELECT ff.fileFolderKey.userFileId FROM FileFolder ff WHERE ff.fileFolderKey.folderId = :folderId)")
    void deleteFilesInFolder(@Param("folderId") Integer folderId);

    UserFile findOneByNameAndUserId(String name, Integer userId);

    void deleteFileByFileIdAndUserId(Integer fileId, Integer userId);

    UserFile findOneByFileId(Integer fileId);

    @Query("SELECT f FROM UserFile f WHERE f.name = :filename AND f.id IN " +
            "(SELECT ff.fileFolderKey.userFileId FROM FileFolder ff WHERE ff.fileFolderKey.folderId = :folderId)")
    UserFile findUserFileInFolder(@Param("filename") String filename, @Param("folderId") Integer folderId);
}
