package com.example.smarthomeapp.model;

import java.util.List;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class Device {

    /**
     * Attributes
     */
    private String id;
    private String refDeviceType;
    private String name;
    private String address;
    private String refDivision;
    private String[] accessLevel;
    private String[] userBlocked;

    /**
     * Elements
     */
    private List<DeviceService> deviceServiceList;

    /**
     * Empty constructor for XML de/serialization
     */
    public Device() {
    }

    /**
     * Constructor
     *
     * @param id                the id
     * @param refDeviceType     the ref device type
     * @param name              the name
     * @param address           the address
     * @param refDivision       the ref division
     * @param accessLevel       the access level
     * @param userBlocked       the user blocked
     * @param deviceServiceList the device service list
     */
    public Device(String id,
                  String refDeviceType,
                  String name,
                  String address,
                  String refDivision,
                  String accessLevel,
                  String userBlocked,
                  List<DeviceService> deviceServiceList) {

        this.id = id;
        this.refDeviceType = refDeviceType;
        this.name = name;
        this.address = address;
        this.refDivision = refDivision;
        this.accessLevel = stripStringIntoV(accessLevel, ",");
        this.userBlocked = stripStringIntoV(userBlocked, ",");
        this.deviceServiceList = deviceServiceList;
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
     * Gets ref device type.
     *
     * @return the ref device type
     */
    public String getRefDeviceType() {
        return refDeviceType;
    }

    /**
     * Sets ref device type.
     *
     * @param refDeviceType the ref device type
     */
    public void setRefDeviceType(String refDeviceType) {
        this.refDeviceType = refDeviceType;
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets ref division.
     *
     * @return the ref division
     */
    public String getRefDivision() {
        return refDivision;
    }

    /**
     * Sets ref division.
     *
     * @param refDivision the ref division
     */
    public void setRefDivision(String refDivision) {
        this.refDivision = refDivision;
    }

    /**
     * Get access level string [ ].
     *
     * @return the string [ ]
     */
    public String[] getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets access level.
     *
     * @param accessLevel the access level
     */
    public void setAccessLevel(String[] accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * Get user blocked string [ ].
     *
     * @return the string [ ]
     */
    public String[] getUserBlocked() {
        return userBlocked;
    }

    /**
     * Sets user blocked.
     *
     * @param userBlocked the user blocked
     */
    public void setUserBlocked(String[] userBlocked) {
        this.userBlocked = userBlocked;
    }

    /**
     * Gets device service list.
     *
     * @return the device service list
     */
    public List<DeviceService> getDeviceServiceList() {
        return deviceServiceList;
    }

    /**
     * Sets device service list.
     *
     * @param deviceServiceList the device service list
     */
    public void setDeviceServiceList(List<DeviceService> deviceServiceList) {
        this.deviceServiceList = deviceServiceList;
    }

    /**
     * Strip string into v string [ ].
     *
     * @param strList   the str list
     * @param separator the separator
     * @return the string [ ]
     */
    private static String[] stripStringIntoV(String strList, String separator){
        return strList.split(separator);
    }

}
