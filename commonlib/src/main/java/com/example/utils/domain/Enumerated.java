package com.example.utils.domain;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class Enumerated {

    private String name;
    private String value;

    /**
     * Constructor
     *
     * @param name  the name
     * @param value the value
     */
    public Enumerated(String name, String value) {
        this.name = name;
        this.value = value;
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
     * Gets value.
     *
     * @return the value
     */
    public String getValue () {
        return value;
    }

    /**
     * Sets value.
     *
     * @param Value the value
     */
    public void setValue (String Value) {
        this.value = Value;
    }

    @Override
    public String toString() {
        return "Enumerated [name = " + name + ", value = " + value + "]";
    }
}
