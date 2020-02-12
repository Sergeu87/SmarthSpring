package com.example.smarthomeapp.divisions.data.remote;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.divisions.data.DivisionsDataSource;
import com.example.smarthomeapp.util.RemoteUtils;
import com.example.smarthomeapp.httpentities.DeviceStateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DivisionsRemoteDataSource implements DivisionsDataSource{

    private static DivisionsRemoteDataSource INSTANCE;
    /**
     * The Service.
     */
    DivisionsService _service;
    /**
     * The Retrofit.
     */
    Retrofit _retrofit = RemoteUtils.getRetrofitObj();

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static DivisionsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DivisionsRemoteDataSource();
        }
        return INSTANCE;
    }


    private DivisionsRemoteDataSource() {


        _service = _retrofit.create(DivisionsService.class);
    }

    @Override
    public void getDevices(String divisionId, @NonNull final LoadDevicesCallback callback) {

        Call<List<DeviceStateResponse>> devicesCall = _service.getDevicesByDivision(divisionId);


        devicesCall.enqueue(new Callback<List<DeviceStateResponse>>() {
            @Override
            public void onResponse(Call<List<DeviceStateResponse>> call, Response<List<DeviceStateResponse>> response) {

                List<DeviceStateResponse> devices = response.body();
                callback.onDevicesLoaded(devices);
            }

            @Override
            public void onFailure(Call<List<DeviceStateResponse>> call, Throwable t) {

                callback.onDataNotAvailable();
            }
        });
    }
}
