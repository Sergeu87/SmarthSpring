package com.example.utils.domain;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class User {

    /**
     * Attributes
     */
    private String id;
    private String name;
    private String password;
    private String accessLevel;

    /**
     * Constructor
     *
     * @param id          the id
     * @param name        the name
     * @param password    the password
     * @param accessLevel the access level
     */
    public User(String id, String name, String password, String accessLevel) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.accessLevel = accessLevel;
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets access level.
     *
     * @return the access level
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets access level.
     *
     * @param accessLevel the access level
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }
}
