package com.example.smarthomeapp.login;

import android.support.annotation.NonNull;

import com.example.smarthomeapp.model.HomeConfigEntity;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    /**
     * Instantiates a new Login presenter.
     *
     * @param loginView the login view
     */
    public LoginPresenter(@NonNull LoginContract.View loginView) {

        mLoginView = loginView;

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadHouseConfiguration(HomeConfigEntity homeConfigEntity) {
        mLoginView.showHouseConfigResult(homeConfigEntity);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }
}
