package com.example.smarthomeapp.devices.data.remote;

import com.example.smarthomeapp.httpentities.DeviceState;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public interface DevicesService {

    /**
     * Gets all devices.
     *
     * @return the all devices
     */
    @GET("devices")
    Call<List<DeviceState>> getAllDevices();


    /**
     * Save device to a remote server
     * @param deviceState device id and its property values
     * @return Returns device with id created by server
     */
    @POST("device")
    Call<DeviceState> saveDevice(@Body DeviceState deviceState);

}
