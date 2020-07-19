package com.example.demo.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name="application_property")
public class ApplicationProperty {





    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    @Id
    private String propertyKey;

    private String propertyValue;
}
