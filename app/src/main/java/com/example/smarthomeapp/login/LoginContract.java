package com.example.smarthomeapp.login;

import com.example.smarthomeapp.BasePresenter;
import com.example.smarthomeapp.BaseView;
import com.example.smarthomeapp.HouseConfigLoadPresenter;
import com.example.smarthomeapp.divisions.DivisionsContract;
import com.example.smarthomeapp.model.Division;
import com.example.smarthomeapp.model.HomeConfigEntity;

import java.util.List;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public interface LoginContract {

    /**
     * The interface View.
     */
    interface View extends BaseView<LoginContract.Presenter> {

        /**
         * Sets loading indicator.
         *
         * @param active the active
         */
        void setLoadingIndicator(boolean active);

        /**
         * Show house config result.
         *
         * @param homeConfigEntity the home config entity
         */
        void showHouseConfigResult(HomeConfigEntity homeConfigEntity);

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
    interface Presenter extends HouseConfigLoadPresenter {

        /**
         * Result.
         *
         * @param requestCode the request code
         * @param resultCode  the result code
         */
        void result(int requestCode, int resultCode);
    }
}
