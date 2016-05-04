package com.almacen.module.file.specification.size;


import com.almacen.module.configuration.service.PropertyService;
import com.almacen.module.file.UserFile;
import com.almacen.specification.AbstractSpecification;

import javax.inject.Inject;


public class UserFileSizeSpecification extends AbstractSpecification<UserFile> {

    @Inject
    private PropertyService propertyService;

    private Integer MB = 1048576;

    @Override
    public Boolean isSatisfiedBy(UserFile candidate) {
        String maximumUploadSizeFile = propertyService.findMaximumUploadSizeFile().getPropertyValue();
        Long maximumUpload = Long.valueOf(maximumUploadSizeFile).longValue() * MB;
        if(maximumUpload <= candidate.getSize())
            return false;
        else
            return true;

    }
}
