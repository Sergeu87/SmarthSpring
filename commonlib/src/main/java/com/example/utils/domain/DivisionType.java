package com.example.utils.domain;

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
