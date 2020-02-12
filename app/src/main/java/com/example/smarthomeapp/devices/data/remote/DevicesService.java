package com.example.smarthomeapp.devices.data.remote;

import com.example.smarthomeapp.httpentities.DeviceStateResponse;
import com.example.smarthomeapp.httpentities.DevicesResponse;
import com.example.utils.domain.Device;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Call<List<DeviceStateResponse>> getAllDevices();


    /**
     * Save device to a remote server
     * @param devicesValues key - device id, value - changed value
     * @return Returns device with id created by server
     */
    @POST("devices")
    Call<DeviceStateResponse> saveDevice(@Body Map<String,String> devicesValues);

}
