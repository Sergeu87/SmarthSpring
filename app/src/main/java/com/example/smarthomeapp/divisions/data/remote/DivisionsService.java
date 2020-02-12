package com.example.smarthomeapp.divisions.data.remote;

import com.example.smarthomeapp.httpentities.DeviceStateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public interface DivisionsService {

    /**
     * Gets devices by division.
     *
     * @param id the id
     * @return the devices by division
     */
    @GET("divisions/{id}/devices")
    Call<List<DeviceStateResponse>> getDevicesByDivision(@Path("id") String id);
}
