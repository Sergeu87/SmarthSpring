package com.example.smarthomeapp.devices.data.remote;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.devices.data.DevicesDataSource;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.util.RemoteUtils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DevicesRemoteDataSource implements DevicesDataSource {

    private static DevicesRemoteDataSource INSTANCE;
    /**
     * The Service.
     */
    DevicesService _service;
    /**
     * The Retrofit.
     */
    Retrofit _retrofit = RemoteUtils.getRetrofitObj();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DevicesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DevicesRemoteDataSource();
        }
        return INSTANCE;
    }


    private DevicesRemoteDataSource() {


        _service = _retrofit.create(DevicesService.class);
    }

    @Override
    public void getAllDevices(@NonNull final LoadDevicesCallback callback) {

        Call<List<DeviceState>> devicesCall = _service.getAllDevices();


        devicesCall.enqueue(new Callback<List<DeviceState>>() {
            @Override
            public void onResponse(Call<List<DeviceState>> call, Response<List<DeviceState>> response) {

                List<DeviceState> devices = response.body();
                callback.onDevicesLoaded(devices);
            }

            @Override
            public void onFailure(Call<List<DeviceState>> call, Throwable t) {

                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateDeviceValue(final @NonNull DeviceState deviceState, final @NonNull UpdateDeviceValueCallback callback) {

        Call<DeviceState> devicesCall = _service.saveDevice(deviceState);

        // Execute the call asynchronously. Get a positive or negative callback.
        devicesCall.enqueue(new Callback<DeviceState>() {
            @Override
            public void onResponse(Call<DeviceState> call, Response<DeviceState> response) {
                callback.onDeviceValueUpdated(true);
            }

            @Override
            public void onFailure(Call<DeviceState> call, Throwable t) {
                // the network call was a failure
                callback.onDataNotAvailable();
            }
        });
    }
}
