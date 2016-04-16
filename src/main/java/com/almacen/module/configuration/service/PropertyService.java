package com.almacen.module.configuration.service;


import com.almacen.module.configuration.Property;

import java.util.List;

public interface PropertyService {
    void saveProperty(Property property);

    void deleteProperty(Property property);

    List<Property> findAll();

    Property findOneByPropertyName(String propertyName);

    Property findBlockedFileExtensions() ;

    Property maximumUploadSizeFile();
}
