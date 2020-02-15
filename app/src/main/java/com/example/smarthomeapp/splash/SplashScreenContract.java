package com.example.smarthomeapp.splash;

import com.example.smarthomeapp.BaseView;
import com.example.smarthomeapp.HouseConfigLoadPresenter;
import com.example.smarthomeapp.login.LoginContract;
import com.example.smarthomeapp.model.HomeConfigEntity;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public interface SplashScreenContract {

    /**
     * The constant MAIN_SCREEN.
     */
    int MAIN_SCREEN = 1;
    /**
     * The constant LOGIN.
     */
    int LOGIN = 2;

    /**
     * The interface View.
     */
    interface View extends BaseView<SplashScreenContract.Presenter> {

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

        /**
         * Enter main screen.
         *
         * @param screenToGoTo the screen to go to
         */
        void enterMainScreen(int screenToGoTo);

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
