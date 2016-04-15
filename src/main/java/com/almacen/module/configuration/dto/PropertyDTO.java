package com.almacen.module.configuration.dto;


public class PropertyDTO {

    private String propertyName;
    private String propertyValue;

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
