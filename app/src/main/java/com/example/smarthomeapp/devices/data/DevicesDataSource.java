package com.example.smarthomeapp.devices.data;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.httpentities.DeviceState;

import java.util.List;
import java.util.Map;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public interface DevicesDataSource {

    /**
     * The interface Load devices callback.
     */
    interface LoadDevicesCallback {

        /**
         * On devices loaded.
         *
         * @param devices the devices
         */
        void onDevicesLoaded(List<DeviceState> devices);

        /**
         * On data not available.
         */
        void onDataNotAvailable();
    }

    /**
     * The interface Update device value callback.
     */
    interface UpdateDeviceValueCallback {

        /**
         * On device value updated.
         *
         * @param isUpdated the is updated
         */
        void onDeviceValueUpdated(boolean isUpdated);

        /**
         * On data not available.
         */
        void onDataNotAvailable();
    }

    /**
     * Gets all devices.
     *
     * @param callback the callback
     */
    void getAllDevices(@NonNull LoadDevicesCallback callback);

    /**
     * Update device value.
     *
     * @param deviceState the device state
     * @param callback      the callback
     */
    void updateDeviceValue(@NonNull DeviceState deviceState, @NonNull UpdateDeviceValueCallback callback);

}
