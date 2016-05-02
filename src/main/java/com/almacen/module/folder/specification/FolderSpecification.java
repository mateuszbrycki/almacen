package com.almacen.module.folder.specification;


import com.almacen.module.folder.Folder;
import com.almacen.specification.AbstractSpecification;
import com.almacen.specification.Specification;

public class FolderSpecification extends AbstractSpecification<Folder> {
    Specification<Folder> illegalCharacter;

    public FolderSpecification(Specification<Folder> illegalCharacter) {
        this.illegalCharacter = illegalCharacter;
    }

    @Override
    public Boolean isSatisfiedBy(Folder candidate) {
        return illegalCharacter.isSatisfiedBy(candidate);
    }
}
