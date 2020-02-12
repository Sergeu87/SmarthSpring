package com.example.server.httpentities;

import java.util.List;

/**
 * Created by Serhii Razovyi on 06-Nov-19.
 */
public class DevicesResponse {

    /**
     * The Devices values.
     */
    public List<DeviceStateResponse> devicesValues;

    /**
     * Instantiates a new Devices response.
     *
     * @param devicesValues the devices values
     */
    public DevicesResponse(List<DeviceStateResponse> devicesValues) {
        this.devicesValues = devicesValues;
    }

    /**
     * Gets devices values.
     *
     * @return the devices values
     */
    public List<DeviceStateResponse> getDevicesValues() {
        return devicesValues;
    }

    /**
     * Sets devices values.
     *
     * @param devicesValues the devices values
     */
    public void setDevicesValues(List<DeviceStateResponse> devicesValues) {
        this.devicesValues = devicesValues;
    }
}
