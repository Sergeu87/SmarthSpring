package com.example.smarthomeapp.divisions.data;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.httpentities.DeviceState;

import java.util.List;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public interface DivisionsDataSource {

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
     * Gets devices.
     *
     * @param divisionId the division id
     * @param callback   the callback
     */
    void getDevices(String divisionId, @NonNull DivisionsDataSource.LoadDevicesCallback callback);
}
