package com.almacen.module.configuration.dto;


import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class PropertyDTO {

    private String propertyName;
    private String propertyValue;

    @NotEmpty
    @Range(min=1,max=100)
    private Long maxSize;

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Override
    public String toString() {
        return "PropertyDTO: {Name: " + this.getPropertyName() +
                ", value: " + this.getPropertyValue() + "}";
    }
}
