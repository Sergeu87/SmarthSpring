package com.example.smarthomeapp.model;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class Service {

    /**
     * Attributes
     */
    private String id;
    private String name;

    /**
     * Empty constructor for XML de/serialization
     */
    public Service() {
    }

    /**
     * Constructor
     *
     * @param id   the id
     * @param name the name
     */
    public Service(String id, String name) {
        this.id = id;
        this.name = name;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
