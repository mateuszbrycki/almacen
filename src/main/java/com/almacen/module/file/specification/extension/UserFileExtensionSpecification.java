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

        // file extension shouldn't be on blocked list
        // so if it is specification is not satisfied => return false
        return !Arrays.asList(blockedExtensions).contains(candidate.getExtension());
    }
}
