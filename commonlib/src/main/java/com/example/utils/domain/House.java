package com.example.utils.domain;

import java.util.List;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class House {

    /**
     * Attributes
     */
    private String id;
    private String name;
    private String address;
    private String phone;

    /**
     * Elements
     */
    private List<Floor> floorList;
    private List<Division> divisionList;

    /**
     * Constructor
     *
     * @param id           the id
     * @param name         the name
     * @param address      the address
     * @param phone        the phone
     * @param floorList    the floor list
     * @param divisionList the division list
     */
    public House(String id, String name, String address, String phone, List<Floor> floorList, List<Division> divisionList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.floorList = floorList;
        this.divisionList = divisionList;
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
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets floor list.
     *
     * @return the floor list
     */
    public List<Floor> getFloorList() {
        return floorList;
    }

    /**
     * Sets floor list.
     *
     * @param floorList the floor list
     */
    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    /**
     * Gets division list.
     *
     * @return the division list
     */
    public List<Division> getDivisionList() {
        return divisionList;
    }

    /**
     * Sets division list.
     *
     * @param divisionList the division list
     */
    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }
}
