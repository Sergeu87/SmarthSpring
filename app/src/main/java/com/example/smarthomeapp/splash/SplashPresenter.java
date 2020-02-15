package com.example.smarthomeapp.splash;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.model.HomeConfigEntity;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class SplashPresenter implements SplashScreenContract.Presenter {

    private final SplashScreenContract.View mSplashView;

    /**
     * Instantiates a new Splash presenter.
     *
     * @param splashView the splash view
     */
    public SplashPresenter(@NonNull SplashScreenContract.View splashView) {

        mSplashView = splashView;

        mSplashView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadHouseConfiguration(HomeConfigEntity homeConfigEntity) {
        mSplashView.showHouseConfigResult(homeConfigEntity);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }
}
