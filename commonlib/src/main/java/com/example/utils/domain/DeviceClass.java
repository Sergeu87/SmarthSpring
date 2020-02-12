package com.example.utils.domain;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class DeviceClass {

    /**
     * Attributes
     */
    private int id;
    private String name;

    /**
     * Constructor
     *
     * @param id   the id
     * @param name the name
     */
    public DeviceClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
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
}
