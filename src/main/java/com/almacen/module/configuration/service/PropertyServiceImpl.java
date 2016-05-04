package com.almacen.module.configuration.service;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.repository.PropertyRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

@Service("propertyService")
@Transactional
public class PropertyServiceImpl implements PropertyService {

    @Resource
    private PropertyRepository propertyRepository;

    @Inject
    private Environment environment;

    @Override
    public void saveProperty(Property property) {

        propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Property property) {
        propertyRepository.delete(property);
    }

    @Override
    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property findOneByPropertyName(String propertyName) {
        return propertyRepository.findOneByPropertyName(propertyName);
    }

    @Override
    public Property findBlockedFileExtensions() {

        return propertyRepository.findOneByPropertyName(environment.getProperty("property.extensions.blocked.name"));
    }

    public Property findMaximumUploadSizeFile() {
        return propertyRepository.findOneByPropertyName(environment.getProperty("property.file.maximum_size"));
    }

    @Override
    public Property findIllegalCharacters() {
        return propertyRepository.findOneByPropertyName(environment.getProperty("property.folder.illegal_character"));
    }
}
