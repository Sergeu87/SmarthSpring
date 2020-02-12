package com.example.utils.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class HomeConfigEntity {


    private static HomeConfigEntity _instance;

    private List<Device> _deviceList;
    private List<DeviceType> _deviceTypeList;
    private List<Service> _serviceList;
    private House _house;
    private List<Floor> _floorList;
    private List<Division> _divisionList;
    private List<DivisionType> _divisionTypeList;
    private List<User> _userList;
    private List<ScalarValueType> _scalarValueList;
    private List<EnumValueType> _enumValueList;

    /**
     * Get instance home config entity.
     *
     * @return the home config entity
     */
    public static HomeConfigEntity getInstance(){
        if (_instance == null){
            return new HomeConfigEntity();
        }
        return _instance;
    }

    /**
     * Getters and Setters
     *
     * @return the devices list
     */
    public List<Device> getDevicesList() {
        return _deviceList;
    }

    /**
     * Gets service list.
     *
     * @return the service list
     */
    public List<Service> getServiceList() {
        return _serviceList;
    }

    private House getHouse() {
        return _house;
    }

    /**
     * Gets floor list.
     *
     * @return the floor list
     */
    public List<Floor> getFloorList() {
        return _floorList;
    }

    /**
     * Gets scalar value type list.
     *
     * @return the scalar value type list
     */
    public List<ScalarValueType> getScalarValueTypeList() {
        return _scalarValueList;
    }

    /**
     * Set floor list.
     *
     * @param floorList the floor list
     */
    public void setFloorList(List<Floor> floorList){
        _floorList = floorList;
    }

    /**
     * Gets enum value list.
     *
     * @return the enum value list
     */
    public List<EnumValueType> getEnumValueList() {
        return _enumValueList;
    }

    /**
     * Sets enum value type list.
     *
     * @param enumValueList the enum value list
     */
    public void setEnumValueTypeList(List<EnumValueType> enumValueList) {
        _enumValueList = enumValueList;
    }

    /**
     * Set scalar value type list.
     *
     * @param scalarValueList the scalar value list
     */
    public void setScalarValueTypeList(List<ScalarValueType> scalarValueList){
        _scalarValueList = scalarValueList;
    }

    /**
     * Get division list list.
     *
     * @return the list
     */
    public List<Division> getDivisionList(){
        return _divisionList;
    }

    /**
     * Set division list.
     *
     * @param divisionList the division list
     */
    public void setDivisionList(List<Division> divisionList){
        _divisionList = divisionList;
    }

    /**
     * Set division type list.
     *
     * @param divisionTypeList the division type list
     */
    public void setDivisionTypeList(List<DivisionType> divisionTypeList){
        _divisionTypeList = divisionTypeList;
    }

    /**
     * Gets user list.
     *
     * @return the user list
     */
    public List<User> getUserList() {
        return _userList;
    }

    /**
     * Sets user list.
     *
     * @param userList the user list
     */
    public void setUserList(List<User> userList) {
        this._userList = userList;
    }

    /**
     * Sets device list.
     *
     * @param deviceList the device list
     */
    public void setDeviceList(List<Device> deviceList) {
        this._deviceList = deviceList;
    }

    /**
     * Sets service list.
     *
     * @param serviceList the service list
     */
    public void setServiceList(List<Service> serviceList) {
        this._serviceList = serviceList;
    }

    /**
     * Sets house.
     *
     * @param house the house
     */
    public void setHouse(House house) {
        this._house = house;
    }

    /**
     * Gets device type list.
     *
     * @return the device type list
     */
    public List<DeviceType> getDeviceTypeList() {
        return _deviceTypeList;
    }

    /**
     * Sets device type list.
     *
     * @param deviceTypeList the device type list
     */
    public void setDeviceTypeList(List<DeviceType> deviceTypeList) {
        _deviceTypeList = deviceTypeList;
    }

    /**
     * Get Object By IDs
     *
     * @param userID the user id
     * @return the user
     */
    public User getUserByID(String userID){
        for(User user : _userList){
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
        for(Device device : _deviceList){
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
        for(Division division : _divisionList){
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
        for(Device device : _deviceList){
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
        for(DeviceType deviceType : _deviceTypeList){
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
        for(ScalarValueType scalarType : _scalarValueList){
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
        for(EnumValueType enumType : _enumValueList){
            if(enumType.getEnumId().equals(enumID)){
                return enumType;
            }
        }
        return null;
    }
}
