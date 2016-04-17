package com.almacen.module.configuration.factory;

import com.almacen.module.configuration.Property;
import com.almacen.module.configuration.PropertyAbstractFactory;
import com.almacen.module.configuration.dto.PropertyDTO;
import org.apache.log4j.Logger;

public class PropertyFactory extends PropertyAbstractFactory {

    private static final Logger logger = Logger.getLogger(PropertyFactory.class);

    @Override
    public Property createFromDTO(PropertyDTO propertyDTO) {
        String preparedValue = propertyDTO.getPropertyValue().replaceAll(", ", ";");

        Property property = new Property();
        property.setPropertyName(propertyDTO.getPropertyName());
        property.setPropertyValue(preparedValue);

        return property;
    }

}
