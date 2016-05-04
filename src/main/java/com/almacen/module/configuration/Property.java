package com.almacen.module.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="system_configuration")
public class Property {

    @Id
    @NotNull
    @JoinColumn(name="property_name")
    @JsonIgnore
    @Column(unique=true)
    private String propertyName;

    @NotNull
    @JoinColumn(name="property_value")
    @JsonIgnore
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
        return "PropertyDTO: { Name: " + this.getPropertyName() +
                ", value: " + this.getPropertyValue() + "}";
    }
}
