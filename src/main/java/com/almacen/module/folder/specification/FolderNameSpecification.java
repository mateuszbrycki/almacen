package com.almacen.module.folder.specification;


import com.almacen.module.configuration.service.PropertyService;
import com.almacen.module.folder.Folder;
import com.almacen.specification.AbstractSpecification;

import javax.inject.Inject;


public class FolderNameSpecification extends AbstractSpecification<Folder> {

    @Inject
    private PropertyService propertyService;

    @Override
    public Boolean isSatisfiedBy(Folder candidate) {
        String[] illegalCharacters = propertyService.findIllegalCharacters().getPropertyValue().split(";");
        for (int i = 0; i < illegalCharacters.length; i++) {
            if ((candidate.getFolder_name()).contains(illegalCharacters[i]))
                return true;
        }
        return false;
    }
}
