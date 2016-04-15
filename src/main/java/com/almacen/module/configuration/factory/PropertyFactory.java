package com.almacen.module.configuration.factory;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.PropertyAbstractFactory;
import com.almacen.module.configuration.dto.PropertyDTO;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;


import javax.inject.Inject;

public class PropertyFactory extends PropertyAbstractFactory {

    @Inject
    private Environment environment;

    private static final Logger logger = Logger.getLogger(PropertyFactory.class);

    @Override
    public Property createFromDTO(PropertyDTO propertyDTO) {

        String preparedValue = propertyDTO.getPropertyValue().replaceAll(", ", ";");

        Property property = new Property();
        property.setPropertyName(environment.getProperty("property.extensions.blocked.name"));
        property.setPropertyValue(preparedValue);

        return property;
    }

}
