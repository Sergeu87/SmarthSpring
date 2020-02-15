package com.example.smarthomeapp.allcontrol;

import com.example.smarthomeapp.BasePresenter;
import com.example.smarthomeapp.BaseView;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.model.Device;

import java.util.List;

/**
 * Created by Serhii Razovyi on 10-Nov-19.
 */
public class AllControlContract {

    /**
     * The interface View.
     */
    interface View extends BaseView<Presenter> {

        /**
         * Sets loading indicator.
         *
         * @param active the active
         */
        void setLoadingIndicator(boolean active);

        /**
         * Show all devices.
         *
         * @param devices      the devices
         * @param devicesState the devices state
         */
        void showAllDevices(List<Device> devices, List<DeviceState> devicesState);

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
         * Load all devices.
         */
        void loadAllDevices();
    }

}
