package com.example.smarthomeapp.model;

/**
 * Created by Serhii Razovyi on 02-Nov-19.
 */
public class DivisionType {

    /**
     * Attributes
     */
    private String id;
    private String name;

    /**
     * Empty constructor for XML de/serialization
     */
    public DivisionType() {
    }

    /**
     * Constructor
     *
     * @param id   the id
     * @param name the name
     */
    public DivisionType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
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

}
