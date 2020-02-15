package com.example.smarthomeapp.devices.data;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.httpentities.DeviceState;

import java.util.List;
import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DevicesRepository implements DevicesDataSource{

    private static DevicesRepository INSTANCE = null;

    private final DevicesDataSource mDevicesRemoteDataSource;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    private DevicesRepository(@NonNull DevicesDataSource devicesRemoteDataSource){
        mDevicesRemoteDataSource = checkNotNull(devicesRemoteDataSource);
    }

    /**
     * Gets instance.
     *
     * @param devicesRemoteDataSource the devices remote data source
     * @return the instance
     */
    public static DevicesRepository getInstance(DevicesDataSource devicesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new DevicesRepository(devicesRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Destroy instance.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getAllDevices(@NonNull final LoadDevicesCallback callback) {
        checkNotNull(callback);

        mDevicesRemoteDataSource.getAllDevices(new LoadDevicesCallback() {
            @Override
            public void onDevicesLoaded(List<DeviceState> deviceStateRespons) {
                callback.onDevicesLoaded(deviceStateRespons);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateDeviceValue(@NonNull Map<String,String> devicesValues, @NonNull UpdateDeviceValueCallback callback) {
        mDevicesRemoteDataSource.updateDeviceValue(devicesValues, callback);
    }
}
