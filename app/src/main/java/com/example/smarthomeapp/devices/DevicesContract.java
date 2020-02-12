package com.example.smarthomeapp.devices;

import com.example.smarthomeapp.BasePresenter;
import com.example.smarthomeapp.BaseView;
import com.example.smarthomeapp.httpentities.DeviceStateResponse;
import com.example.utils.domain.Device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Serhii Razovyi on 07-Nov-19.
 */
public class DevicesContract {

    /**
     * The interface View.
     */
    public interface View extends BaseView<DevicesContract.Presenter> {

        /**
         * Sets loading indicator.
         *
         * @param active the active
         */
        void setLoadingIndicator(boolean active);

        /**
         * Show devices.
         *
         * @param devices      the devices
         * @param devicesState the devices state
         */
        void showDevices(List<Device> devices, List<DeviceStateResponse> devicesState);

        /**
         * Show no devices.
         */
        void showNoDevices();

        /**
         * Is active boolean.
         *
         * @return the boolean
         */
        boolean isActive();

        /**
         * Show failed update.
         */
        void showFailedUpdate();

        /**
         * Show successful update.
         */
        void showSuccessfulUpdate();
    }

    /**
     * The interface Presenter.
     */
    public interface Presenter extends BasePresenter {

        /**
         * Result.
         *
         * @param requestCode the request code
         * @param resultCode  the result code
         */
        void result(int requestCode, int resultCode);

        /**
         * Save device value.
         *
         * @param devicesValues the devices values
         */
        void saveDeviceValue(Map<String,String> devicesValues);

        /**
         * Load devices.
         */
        void loadDevices();

        /**
         * Gets device values to save.
         *
         * @return the device values to save
         */
        Map<String,String> getDeviceValuesToSave();

    }
}
