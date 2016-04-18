package com.almacen.module.file.specification;

import com.almacen.module.file.UserFile;
import com.almacen.specification.AbstractSpecification;
import com.almacen.specification.Specification;

public class UserFileSpecification extends AbstractSpecification<UserFile> {
    Specification<UserFile> extensionSpecification;
    Specification<UserFile> userFileSizeSpecification;

    public UserFileSpecification(Specification<UserFile> extensionSpecification,
                                 Specification<UserFile> userFileSizeSpecification) {
        this.extensionSpecification = extensionSpecification;
        this.userFileSizeSpecification = userFileSizeSpecification;
    }

    @Override
    public Boolean isSatisfiedBy(UserFile candidate) {
        return extensionSpecification.and(userFileSizeSpecification).isSatisfiedBy(candidate);
    }
}
