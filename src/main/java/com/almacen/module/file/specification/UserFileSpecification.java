package com.almacen.module.file.specification;

import com.almacen.module.file.UserFile;
import com.almacen.specification.AbstractSpecification;
import com.almacen.specification.Specification;

public class UserFileSpecification extends AbstractSpecification<UserFile> {
    Specification<UserFile> extensionSpecification;

    public UserFileSpecification(Specification<UserFile> extensionSpecification) {
        this.extensionSpecification = extensionSpecification;
    }

    @Override
    public Boolean isSatisfiedBy(UserFile candidate) {
        return extensionSpecification.isSatisfiedBy(candidate);
    }
}
