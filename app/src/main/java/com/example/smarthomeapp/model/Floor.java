package com.example.smarthomeapp.model;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class Floor {

    /**
     * Attributes
     */
    private String id;
    private String name;
    private String heightOrder;

    /**
     * Empty constructor for XML de/serialization
     */
    public Floor() {
    }

    /**
     * Constructor
     *
     * @param id          the id
     * @param name        the name
     * @param heightOrder the height order
     */
    public Floor(String id, String name, String heightOrder) {
        this.id = id;
        this.name = name;
        this.heightOrder = heightOrder;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
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
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets height order.
     *
     * @return the height order
     */
    public String getHeightOrder() {
        return heightOrder;
    }

    /**
     * Sets height order.
     *
     * @param heightOrder the height order
     */
    public void setHeightOrder(String heightOrder) {
        this.heightOrder = heightOrder;
    }
}