package com.example.server.httpentities;

import java.util.List;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class DeviceStateResponse {

    /**
     * The Device id.
     */
    public String deviceId;
    /**
     * The Values.
     */
    public List<PropertyValueResponse> values;

    /**
     * Gets device id.
     *
     * @return the device id
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * Sets device id.
     *
     * @param deviceId the device id
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets values.
     *
     * @return the values
     */
    public List<PropertyValueResponse> getValues() {
        return values;
    }

    /**
     * Sets values.
     *
     * @param values the values
     */
    public void setValues(List<PropertyValueResponse> values) {
        this.values = values;
    }
}
