package com.almacen.module.storage.repository;

import com.almacen.module.storage.FileFolder;
import com.almacen.module.storage.FileFolderKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileFolderRepository extends JpaRepository<FileFolder, FileFolderKey> {

}
