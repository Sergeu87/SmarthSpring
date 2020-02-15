package com.example.smarthomeapp.model;

import java.util.List;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class EnumValueType {

    private String name;
    private String enumId;
    private List<com.example.smarthomeapp.model.Enumerated> enumerated;

    /**
     * Empty constructor for XML de/serialization
     */
    public EnumValueType() {
    }

    /**
     * Constructor
     *
     * @param name       the name
     * @param enumId     the enum id
     * @param enumerated the enumerated
     */
    public EnumValueType(String name, String enumId, List<com.example.smarthomeapp.model.Enumerated> enumerated) {
        this.name = name;
        this.enumId = enumId;
        this.enumerated = enumerated;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param Name the name
     */
    public void setName(String Name) {
        this.name = Name;
    }

    /**
     * Gets enum id.
     *
     * @return the enum id
     */
    public String getEnumId() {
        return enumId;
    }

    /**
     * Sets enum id.
     *
     * @param enumId the enum id
     */
    public void setEnumId(String enumId) {
        this.enumId = enumId;
    }

    /**
     * Gets enumerated.
     *
     * @return the enumerated
     */
    public List<com.example.smarthomeapp.model.Enumerated> getEnumerated() {
        return enumerated;
    }

    /**
     * Sets enumerated.
     *
     * @param Enumerated the enumerated
     */
    public void setEnumerated(List<Enumerated> Enumerated) {
        this.enumerated = Enumerated;
    }

    @Override
    public String toString() {
        return "EnumValueType [name = " + name + ", ID = " + enumId
                + ", enumerated = " + enumerated + "]";
    }
}
