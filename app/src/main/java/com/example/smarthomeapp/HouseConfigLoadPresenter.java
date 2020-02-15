package com.example.smarthomeapp;

import com.example.smarthomeapp.model.HomeConfigEntity;

/**
 * Created by Serhii Razovyi on 05-Nov-19.
 */
public interface HouseConfigLoadPresenter extends BasePresenter {

    /**
     * Load house configuration.
     *
     * @param homeConfigEntity the home config entity
     */
    void loadHouseConfiguration(HomeConfigEntity homeConfigEntity);

}
