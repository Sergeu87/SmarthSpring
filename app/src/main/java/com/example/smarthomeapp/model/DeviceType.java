package com.example.smarthomeapp.model;

import java.util.List;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class DeviceType {

    private String id;
    private String name;
    private String description;
    private String refDeviceClass;
    private List<Property> propertyList;

    /**
     * Empty constructor for XML de/serialization
     */
    public DeviceType() {
    }

    /**
     * Instantiates a new Device type.
     *
     * @param id             the id
     * @param name           the name
     * @param description    the description
     * @param refDeviceClass the ref device class
     * @param propertyList   the property list
     */
    public DeviceType(String id,
                      String name,
                      String description,
                      String refDeviceClass,
                      List<Property> propertyList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.refDeviceClass = refDeviceClass;
        this.propertyList = propertyList;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId () {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId (String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName () {
        return name;
    }

    /**
     * Sets name.
     *
     * @param Name the name
     */
    public void setName (String Name) {
        this.name = Name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription () {
        return description;
    }

    /**
     * Sets description.
     *
     * @param Description the description
     */
    public void setDescription (String Description) {
        this.description = Description;
    }

    /**
     * Gets ref device class.
     *
     * @return the ref device class
     */
    public String getRefDeviceClass () {
        return refDeviceClass;
    }

    /**
     * Sets ref device class.
     *
     * @param RefDeviceClass the ref device class
     */
    public void setRefDeviceClass (String RefDeviceClass) {
        this.refDeviceClass = RefDeviceClass;
    }

    /**
     * Gets property list.
     *
     * @return the property list
     */
    public List<Property> getPropertyList () {
        return propertyList;
    }

    /**
     * Sets property list.
     *
     * @param PropertyList the property list
     */
    public void setPropertyList (List<Property> PropertyList) {
        this.propertyList = PropertyList;
    }

    @Override
    public String toString() {
        return "DeviceType [id = " + id + ", name = " + name + ", description = " + description
                + ", refDeviceClass = " + refDeviceClass + ", propertyList = " + propertyList + "]";
    }
}
