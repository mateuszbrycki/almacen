package com.almacen.module.configuration;

import com.almacen.AbstractFactory;
import com.almacen.module.configuration.dto.PropertyDTO;

public abstract class PropertyAbstractFactory implements AbstractFactory {
    public abstract Property createFromDTO(PropertyDTO propertyDTO);
}
