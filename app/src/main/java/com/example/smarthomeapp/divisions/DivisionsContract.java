package com.example.smarthomeapp.divisions;

import com.example.smarthomeapp.BasePresenter;
import com.example.smarthomeapp.BaseView;
import com.example.smarthomeapp.httpentities.DeviceState;
import com.example.smarthomeapp.model.Division;

import java.util.List;

/**
 * Created by Serhii Razovyi on 20-Oct-19.
 */
public interface DivisionsContract {

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
         * Show divisions.
         *
         * @param tasks the tasks
         */
        void showDivisions(List<Division> tasks);

        /**
         * Show division devices ui.
         *
         * @param divisionId the division id
         * @param devices    the devices
         */
        void showDivisionDevicesUi(String divisionId, List<DeviceState> devices);

        /**
         * Show no divisions.
         */
        void showNoDivisions();

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
    interface Presenter extends BasePresenter {

        /**
         * Result.
         *
         * @param requestCode the request code
         * @param resultCode  the result code
         */
        void result(int requestCode, int resultCode);

        /**
         * Open devices list.
         *
         * @param divisionPosition the division position
         */
        void openDevicesList(int divisionPosition);

        /**
         * Load divisions.
         */
        void loadDivisions();

    }
}
