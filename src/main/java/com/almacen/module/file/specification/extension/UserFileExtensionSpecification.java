package com.almacen.module.file.specification.extension;

import com.almacen.module.configuration.service.PropertyService;
import com.almacen.module.file.UserFile;
import com.almacen.specification.AbstractSpecification;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.util.Arrays;

public class UserFileExtensionSpecification extends AbstractSpecification<UserFile> {

    @Inject
    private PropertyService propertyService;

    @Override
    public Boolean isSatisfiedBy(UserFile candidate) {
        String[] blockedExtensions = propertyService.findBlockedFileExtensions().getPropertyValue().split(";");

        return Arrays.asList(blockedExtensions).contains(candidate.getExtension());
    }
}
