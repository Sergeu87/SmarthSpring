package com.example.server.httpentities;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class PropertyValueResponse {

    /**
     * The Property id.
     */
    public String propertyId;
    /**
     * The Property value.
     */
    public String propertyValue;

    /**
     * Instantiates a new Property value response.
     *
     * @param propertyId    the property id
     * @param propertyValue the property value
     */
    public PropertyValueResponse(String propertyId, String propertyValue) {
        this.propertyId = propertyId;
        this.propertyValue = propertyValue;
    }

    /**
     * Gets property id.
     *
     * @return the property id
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * Sets property id.
     *
     * @param propertyId the property id
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * Gets property value.
     *
     * @return the property value
     */
    public String getPropertyValue() {
        return propertyValue;
    }

    /**
     * Sets property value.
     *
     * @param propertyValue the property value
     */
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
