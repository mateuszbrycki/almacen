package com.almacen.config.specification;


import com.almacen.module.folder.Folder;
import com.almacen.module.folder.specification.FolderIllegalCharacterSpecification;
import com.almacen.module.folder.specification.FolderSpecification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.almacen.specification.Specification;

@Configuration
public class FolderSpecificationConfig {

    @Bean
    @Qualifier("folderIllegalCharacter")
    public Specification<Folder> folderIllegalCharacterSpecification() {
        return new FolderIllegalCharacterSpecification();
    }

    @Bean
    public FolderSpecification folderSpecification(
            @Qualifier("folderIllegalCharacter") Specification<Folder> folderIllegalCharacter) {
        return new FolderSpecification(folderIllegalCharacter);
    }
}

