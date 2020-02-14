package com.example.utils.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class HomeConfigEntity {

    private List<Device> deviceList;
    private List<DeviceType> deviceTypeList;
    private List<Service> serviceList;
    private House house;
    private List<Floor> floorList;
    private List<Division> divisionList;
    private List<DivisionType> divisionTypeList;
    private List<User> userList;
    private List<ScalarValueType> scalarValueList;
    private List<EnumValueType> enumValueList;

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(List<DeviceType> deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }

    public List<Division> getDivisionList() {
        return divisionList;
    }

    public void setDivisionList(List<Division> divisionList) {
        this.divisionList = divisionList;
    }

    public List<DivisionType> getDivisionTypeList() {
        return divisionTypeList;
    }

    public void setDivisionTypeList(List<DivisionType> divisionTypeList) {
        this.divisionTypeList = divisionTypeList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<ScalarValueType> getScalarValueList() {
        return scalarValueList;
    }

    public void setScalarValueList(List<ScalarValueType> scalarValueList) {
        this.scalarValueList = scalarValueList;
    }

    public List<EnumValueType> getEnumValueList() {
        return enumValueList;
    }

    public void setEnumValueList(List<EnumValueType> enumValueList) {
        this.enumValueList = enumValueList;
    }

    /**
     * Get Object By IDs
     *
     * @param userID the user id
     * @return the user
     */
    public User getUserByID(String userID){
        for(User user : userList){
            if(userID.equals(user.getId())){
                return user;
            }
        }
        return null;
    }

    /**
     * Get device by id device.
     *
     * @param deviceID the device id
     * @return the device
     */
    public Device getDeviceByID(String deviceID){
        for(Device device : deviceList){
            if(deviceID.equals(device.getId())){
                return device;
            }
        }
        return null;
    }

    /**
     * Get division by id division.
     *
     * @param divisionID the division id
     * @return the division
     */
    public Division getDivisionByID(String divisionID){
        for(Division division : divisionList){
            if(divisionID.equals(division.getId())){
                return division;
            }
        }
        return null;
    }

    /**
     * Get devices by division id list.
     *
     * @param divisionID the division id
     * @return the list
     */
    public List<Device> getDevicesByDivisionID(String divisionID){
        List<Device> devicesList = new ArrayList<>();
        for(Device device : deviceList){
            if(device.getRefDivision().equals(divisionID)){
                devicesList.add(device);
            }
        }
        return devicesList;
    }

    /**
     * Get device type by id device type.
     *
     * @param deviceTypeID the device type id
     * @return the device type
     */
    public DeviceType getDeviceTypeByID(String deviceTypeID){
        for(DeviceType deviceType : deviceTypeList){
            if(deviceType.getId().equals(deviceTypeID)){
                return deviceType;
            }
        }
        return null;
    }

    /**
     * Get property list by device type id list.
     *
     * @param deviceTypeID the device type id
     * @return the list
     */
    public List<Property> getPropertyListByDeviceTypeID(String deviceTypeID){
        return getDeviceTypeByID(deviceTypeID).getPropertyList();
    }

    /**
     * Gets property from device type by id.
     *
     * @param deviceTypeID the device type id
     * @param propertyID   the property id
     * @return the property from device type by id
     */
    public Property getPropertyFromDeviceTypeByID(String deviceTypeID, String propertyID) {
        List<Property> properties = getDeviceTypeByID(deviceTypeID).getPropertyList();
        for(Property property : properties){
            if(property.getId().equals(propertyID)){
                return property;
            }
        }
        return null;
    }

    /**
     * Get scalar by id scalar value type.
     *
     * @param scalarID the scalar id
     * @return the scalar value type
     */
    public ScalarValueType getScalarByID(String scalarID){
        for(ScalarValueType scalarType : scalarValueList){
            if(scalarType.getScalarId().equals(scalarID)){
                return scalarType;
            }
        }
        return null;
    }

    /**
     * Get enum by id enum value type.
     *
     * @param enumID the enum id
     * @return the enum value type
     */
    public EnumValueType getEnumByID(String enumID){
        for(EnumValueType enumType : enumValueList){
            if(enumType.getEnumId().equals(enumID)){
                return enumType;
            }
        }
        return null;
    }
}
